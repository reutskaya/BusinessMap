package com.example.BusinessMap.database;

import com.example.BusinessMap.database.entity.Type;
import com.example.BusinessMap.database.entity.Place;
import com.example.BusinessMap.database.repositories.PlaceRepository;
import com.example.BusinessMap.database.repositories.TypeRepository;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Metrics;
import org.springframework.data.geo.Point;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Querys {

    public static List<Place> getOnlyNeed(PlaceRepository placeRepository, double x, double y, int km) {
        Point point = new Point(59.932229, 30.330791);
        Distance distance = new Distance(1, Metrics.KILOMETERS);
        List<Place> places = placeRepository.findByLocationNear(point, distance);
        return places;
    }

    public static String getPlaces(PlaceRepository placeRepository, TypeRepository typeRepository, double x, double y, int km
    ) {
        JsonObject mainObject = new JsonObject(); // создаем главный объект
        JsonObject rootObject = new JsonObject(); // создаем главный объект
        JsonObject childObject = new JsonObject(); // создаем объект Type
        List<String> category = Arrays.asList("Food", "Entertainment", "Hotel", "Store", "Beauty", "Health");
        StringBuilder builder = new StringBuilder();
        String rez = "";
        int sumPlace = 0;
        int sumType = 0;
        double reit = 0;
        double price = 0;
        Point point = new Point(x, y);
        // например 59.932229, 30.330791
        Distance distance = new Distance(km, Metrics.KILOMETERS);
        // например 50
        List<Place> places = placeRepository.findByLocationNear(point, distance);
        for (int f = 0; f < category.size(); f++) {
            List<Type> bisenessTypes = typeRepository.findByCategory(category.get(f));
            //     builder.append(rez).append("[").append(category.get(f)).append(": ");
            rootObject.addProperty("name", category.get(f));

            for (int i = 0; i < bisenessTypes.size(); i++) {
                String type = bisenessTypes.get(i).getName();
                for (int q = 0; q < places.size(); q++) {
                    String place = places.get(q).getType();
                    if (type.equals(place)) {
                        sumPlace = sumPlace + 1;
                        reit = reit + places.get(q).getRating();
                        price = price + places.get(q).getPrice();
                    }
                }
                //   builder.append("[").append(type).append(" col:").append(sumPlace);
                childObject.addProperty("name", type);
                childObject.addProperty("col", sumPlace);
                if (reit / sumPlace > 0) {
                    if (price / sumPlace > 0) {
                        //  builder.append(" reit:").append(reit / sumPlace).append(" price:").append(price / sumPlace).append("] ");
                        childObject.addProperty("reit", reit / sumPlace);
                        childObject.addProperty("price", price / sumPlace);
                    } else {
                        //  builder.append(" reit:").append(reit / sumPlace).append(" price:").append(0).append("] ");
                        childObject.addProperty("reit", reit / sumPlace);
                        childObject.addProperty("price", 0);
                    }
                } else {
                    //  builder.append(" reit:").append(0).append(" price:").append(0).append("] ");
                    childObject.addProperty("reit", 0);
                    childObject.addProperty("price", 0);
                }
                sumType = sumType + sumPlace;
                sumPlace = 0;
                reit = 0;
                price = 0;
            }

            //  builder.append("totalCol:").append(sumType).append("] \n");
            rootObject.addProperty("totalCol", sumType);
            rootObject.add("types", childObject); // сохраняем дочерний объект в поле "type"
            sumType = 0;
            Gson gson = new Gson();
            rez = gson.toJson(rootObject); // генерация json строки
            builder.append(rez);
        }
        rez = builder.toString();
        return rez;
    }
}