package com.example.BusinessMap.parser;

import com.example.BusinessMap.database.entity.Place;
import com.example.BusinessMap.database.entity.Type;
import com.example.BusinessMap.database.repositories.PlaceRepository;
import com.example.BusinessMap.database.repositories.TypeRepository;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.solr.core.geo.Point;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Parser {
    @Autowired
    private PlaceRepository placeRepository;
    private TypeRepository typeRepository;

    public Parser(PlaceRepository placeRepository, TypeRepository typeRepository) {
        this.placeRepository = placeRepository;
        this.typeRepository = typeRepository;
    }

    public void parse(){
        Map<String, String> type = defineTypes();

        for (Map.Entry<String, String> entry : type.entrySet()) {
            parseType(entry);
        }
    }

    private Map<String, String> defineTypes() {
        Map<String, String> type = new HashMap<>();
        //еда
        type.put("Кафе", "kafe");
        type.put("Ресторан", "restoranyi");
        type.put("Бар", "baryi");
        type.put("Булочная", "bulochnyie");
        //здоровье
        type.put("Аптека", "apteki");
        type.put("Фитнес-клуб", "fitnes-kluby");
        type.put("Медицинский центр", "medicinskie-centry");
        //покупки
        type.put("Автомагазины", "avtomagaziny");
        type.put("Магазин сантехники", "magaziny-santekhniki");
        type.put("Магазин электроники", "magaziny-elektroniki");
        //красота
        type.put("Салон красоты", "salony-krasoty");
        type.put("Парикмахерская", "parikmaherskie");
        //развлечения
        type.put("Парк аттракционов", "parki-attraktsionov");
        type.put("Зоопарк", "zooparki");
        //гостиницы
        type.put("Хостел", "hostelyi");
        type.put("Гостиница", "gostinicy");
        return type;
    }

    private void parseType(@NotNull Map.Entry<String, String> type) {
        String userAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.110 Safari/537.36";
        Document doc;
        Element name;
        Point point = null;
        Elements organisations, addresses;

        //добавляем текуищий тип в базу
        Type newType = new Type(type.getKey());
        typeRepository.save(newType);

        //определяем, сколько страниц выдал поиск по данному типу
        int lastPage = 0;
        try {
            doc = Jsoup.connect("http://spb.spravker.ru/" + type.getValue() + "/")
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
                doc = Jsoup.connect("http://spb.spravker.ru/" + type.getValue() + "/page-" + i)
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
                            newType,
                            rating,
                            0);
                    this.placeRepository.save(newPlace);
                }
            } catch (NullPointerException | IOException e) {
                e.printStackTrace();
            }

            i++;
        } while (i < lastPage);
    }

    private double getRating(@NotNull Elements organisations, int k) {
        Elements stars;
        stars = organisations.get(k).select(".rating");
        double rating = 0;
        int j = 0;
        while (j < 5) {
            if (stars.select("i").hasClass("fa fa-star-half-o")) {
                rating += 0.5;

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
        String apiKey = "a426df02-396e-4ab5-844a-8c3ce394804d";

        //сортируем адреса от мусора
        if (address.text().toLowerCase().contains("Санкт".toLowerCase())) {
            try {
                //по api геокодера яндекса получаем json, в котором наш адрес преобразован в координаты
                json = Jsoup.connect("https://geocode-maps.yandex.ru/1.x/?apikey="
                        + apiKey + "&format=json&geocode="
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