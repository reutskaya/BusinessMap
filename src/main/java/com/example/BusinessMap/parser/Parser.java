package com.example.BusinessMap.parser;

import com.example.BusinessMap.entity.Place;
import com.example.BusinessMap.entity.Type;
import com.example.BusinessMap.repositories.PlaceRepository;
import com.example.BusinessMap.repositories.TypeRepository;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.bson.types.ObjectId;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.solr.core.geo.Point;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableScheduling
public class Parser {
    private static final String API_KEY = "a426df02-396e-4ab5-844a-8c3ce394804d";

    private final PlaceRepository placeRepository;
    private final TypeRepository typeRepository;

    @Autowired
    public Parser(final PlaceRepository placeRepository, final TypeRepository typeRepository) {
        this.placeRepository = placeRepository;
        this.typeRepository = typeRepository;

    }

    //@Scheduled(cron = "0 15 10 0/30 ? ?") //30 числа каждого месяца в 10:15
    @Scheduled(cron = "0 58 1 7 12 6")
    public void parse() {
        StaticMap.mapOfTypes.forEach(this::parseType);
    }

    private void parseType(String typeName, String typeURL) {
        String userAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.110 Safari/537.36";
        Document doc;
        Element name;
        Point point = null;
        Elements organisations, addresses;
        List<Place> placesAlreadyExist = this.placeRepository.findAll();
        List<Place> placeListToAdd = new ArrayList<>();
        List<Type> typeList = this.typeRepository.findAll();

        ObjectId typeId = null;
        for (Type t : typeList) {
            if(t.getName().equals(typeName)){
                typeId = new ObjectId(t.getId());
            }
        }

        //определяем, сколько страниц выдал поиск по данному типу
        int lastPage = 0;
        try {
            doc = Jsoup.connect("http://spb.spravker.ru/" + typeURL + "/")
                    .userAgent(userAgent)
                    .get();
            lastPage = Integer.parseInt(doc.select(".col-left .pagination .frame a")
                    .last()
                    .text());
        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
        }

        //парсим и добавляем в базу
        int i = 1;
        do {
            try {
                doc = Jsoup.connect("http://spb.spravker.ru/" + typeURL + "/page-" + i)
                        .userAgent(userAgent)
                        .get();
                //организации
                organisations = doc.select(".col-left .addresses-list .list-item.hover");
                for (int k = 0; k < 50; k++) {

                    //названиe
                    name = organisations.get(k).select("h3 a").first();

                    //адреса
                    addresses = organisations.get(k).select(".row .right");
                    for (Element address : addresses) {
                        point = getPoint(point, organisations, k, address);
                    }

                    //рейтинг
                    double rating = getRating(organisations, k);

                    //создаем и сохраняем в базу новое место
                    Place newPlace = new Place(
                            name.text(),
                            point,
                            typeId,
                            rating,
                            0);
                    if (placesAlreadyExist.contains(newPlace)) {
                        i++;
                    } else {
                        placeListToAdd.add(newPlace);
                    }
                }
            } catch (NullPointerException | IOException e) {
                e.printStackTrace();
            }

            i++;
        } while (i < lastPage);

        this.placeRepository.saveAll(placeListToAdd);
    }

    private double getRating(@NotNull Elements organisations, int k) {
        Elements stars;
        stars = organisations.get(k).select(".rating");
        double rating = 0;
        int j = 0;
        while (j < 5) {
            if (stars.select("i").hasClass("fa fa-star-half-o")) {
                rating += 0.5;
                stars.select("i").first().remove();
            } else if (stars.select("i").hasClass("fa fa-star")) {
                rating++;
                stars.select("i").first().remove();
            }
            j++;
        }
        return rating;
    }

    private Point getPoint(Point point, Elements organisations, int k, @NotNull Element address) {
        Document json;

        //сортируем адреса от мусора
        if (address.text().toLowerCase().contains("Санкт".toLowerCase())) {
            try {
                //по api геокодера яндекса получаем json, в котором наш адрес преобразован в координаты
                json = Jsoup.connect("https://geocode-maps.yandex.ru/1.x/?apikey="
                        + API_KEY + "&format=json&geocode="
                        + address.text()
                        + "&results=1")
                        .ignoreContentType(true)
                        .get();
                //получаем точку с координатами из json
                point = getPointFromJson(json.text());
            } catch (IOException e) {
                e.printStackTrace();
            } catch (IndexOutOfBoundsException e1) {
                organisations.get(k).remove();
            }
        }
        return point;
    }

    @NotNull
    @Contract("_ -> new")
    private static Point getPointFromJson(String json) {
        String[] coords;
        //парсим json чтобы добраться до Point
        JsonElement jelement = new JsonParser().parse(json);
        JsonObject jobject = jelement.getAsJsonObject();
        jobject = jobject.getAsJsonObject("response").getAsJsonObject("GeoObjectCollection");
        JsonArray jarray = jobject.getAsJsonArray("featureMember");
        jobject = jarray.get(0).getAsJsonObject();
        jobject = jobject.getAsJsonObject("GeoObject").getAsJsonObject("Point");

        //берем pos точки и приводим к нужному виду
        String pos = jobject.get("pos").toString().replace("\"", "");
        coords = pos.split(" ");

        return new Point(Double.parseDouble(coords[1]), Double.parseDouble(coords[0]));
    }
}