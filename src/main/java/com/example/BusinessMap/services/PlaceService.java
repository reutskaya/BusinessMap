package com.example.BusinessMap.services;

import com.example.BusinessMap.entity.Type;
import com.example.BusinessMap.entity.Place;
import com.example.BusinessMap.repositories.PlaceRepository;
import com.example.BusinessMap.repositories.TypeRepository;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Metrics;
import org.springframework.data.geo.Point;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class PlaceService {
    private List<Place> placeList;
    private String PlacesInformation;

    final private PlaceRepository placeRepository;
    final private TypeRepository typeRepository;

    @Autowired
    public PlaceService(PlaceRepository placeRepository, TypeRepository typeRepository) {
        this.placeRepository = placeRepository;
        this.typeRepository = typeRepository;
    }

    public List<Place> getAllPlaces() {
        return this.placeRepository.findAll();
    }

    public List<Place> getPlaceList(double x, double y, int km) {
        Point point = new Point(x, y);
        Distance distance = new Distance(km, Metrics.KILOMETERS);
        placeList = this.placeRepository.findByLocationNear(point, distance);
        return placeList;
    }

    public void setPlaceList(List<Place> placeList) {
        this.placeList = placeList;
    }

    public String getPlacesInformation(double x, double y, float km) {
        JsonArray mainObject = new JsonArray(); // создаем главный объект
        List<String> category = Arrays.asList("Еда", "Развлечения", "Гостиницы", "Покупки", "Красота", "Здоровье");

        String rez = "";
        int sumPlace = 0;
        int sumType = 0;
        double reitType = 0;
        double priceType = 0;
        double reit = 0;
        double price = 0;
        Point point = new Point(x, y);

        // например 59.932229, 30.330791
        Distance distance = new Distance(km, Metrics.KILOMETERS);
        // например 50
        List<Place> places = this.placeRepository.findByLocationNear(point, distance);
        for (String s : category) {
            JsonArray array = new JsonArray();
            JsonObject rootObject = new JsonObject(); // создаем главный объект
            List<Type> types = this.typeRepository.findByCategory(s);
            rootObject.addProperty("name", s);
            rootObject.addProperty("types", 0);
            rootObject.addProperty("name", s);


            for (Type type : types) {
                JsonObject childObject = new JsonObject(); // создаем объект Type
                String bisenessType = type.getId();
                for (Place place : places) {
                    String placeType = place.getType().toString();
                    if (placeType.equals(bisenessType)) {
                        sumPlace = sumPlace + 1;
                        reit = reit + place.getRating();
                        price = price + place.getPrice();

                    }
                    childObject.addProperty("name", type.getName());
                    childObject.addProperty("col", sumPlace);
                    if (reit / sumPlace > 0) {
                        if (price / sumPlace > 0) {
                            childObject.addProperty("reit", Math.round(reit / sumPlace * 10) / 10D);
                            childObject.addProperty("price", Math.round(price / sumPlace));
                        } else {
                            childObject.addProperty("reit", Math.round(reit / sumPlace * 10) / 10D);
                            childObject.addProperty("price", 0);
                        }
                    } else {
                        childObject.addProperty("reit", 0);
                        childObject.addProperty("price", 0);
                    }
 /*                   sumType = sumType + sumPlace;
                    sumPlace = 0;
                    reit = 0;
                    price = 0; */
                }

                sumType = sumType + sumPlace;
                reitType = reitType + reit;
                priceType = priceType + price;
                sumPlace = 0;
                reit = 0;
                price = 0;
                array.add(childObject);
            }
            QualityAssessment(rootObject, sumType, reitType / sumType, priceType / sumType);
            rootObject.add("types", array); // сохраняем дочерний объект в поле "type"
            sumType = 0;
            reitType = 0;
            priceType = 0;
            mainObject.add(rootObject);
            Gson gson = new Gson();
            PlacesInformation = gson.toJson(mainObject); // генерация json строки
        }
        return PlacesInformation;
    }

    public void setPlacesInformation(String placesInformation) {
        PlacesInformation = placesInformation;
    }

    public JsonObject QualityAssessment(JsonObject rootObject, int sumType, double averageReit, double averagePrice) {
        int quality = 0;
        StringBuilder builder = new StringBuilder();
        if (sumType > 0) {
            if (sumType <= 5) {
                rootObject.addProperty("reitCol", 1);
                builder.append("На выбраной вами области мало мест данной категории. ");
            }
            if (sumType > 5 & sumType < 10) {
                rootObject.addProperty("reitCol", 2);
                builder.append("На выбраной вами области достаточно мест данной категории. ");
            }
            if (sumType >= 10) {
                rootObject.addProperty("reitCol", 3);
                builder.append("На выбраной вами области много мест данной категории. ");

            }
        } else {
            rootObject.addProperty("reitCol", 1);
            builder.append("На выбраной вами области нет мест данной категории. ");
        }

        if (averageReit > 0) {
            if (averageReit <= 2) {
                quality = 3;
                builder.append("Их рейтинг низкий и ");
            }
            if (averageReit > 2 & averageReit < 4) {
                quality = 2;
                builder.append("Их рейтинг средний и ");
            }
            if (averageReit >= 4) {
                quality = 1;
                builder.append("Их рейтинг высокий и ");
            }
        } else {
            quality = 3;
            builder.append("Нет информации о их рейтинге и ");

        }

        if (averagePrice > 0) {
            if (averagePrice <= 400) {
                if (quality <= 2) {
                    quality = quality + 1;
                }
                builder.append("чек низкий.");
            }
            if (averagePrice > 400 & averagePrice < 800) {
                builder.append("чек средний.");
            }
            if (averagePrice >= 800) {
                if (quality >= 2) {
                    quality = quality - 1;
                }
                builder.append("чек высокий.");
            }
        } else {
            builder.append("нет информации о среднем чеке.");
        }

        rootObject.addProperty("quality", quality);
        rootObject.addProperty("verdict", builder.toString());
        return rootObject;
    }

}