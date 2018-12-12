package com.example.BusinessMap.services;

import com.example.BusinessMap.entity.Type;
import com.example.BusinessMap.entity.Place;
import com.example.BusinessMap.repositories.PlaceRepository;
import com.example.BusinessMap.repositories.TypeRepository;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Metrics;
import org.springframework.data.geo.Point;

import java.util.Arrays;
import java.util.List;

public class PlaceService {
    private List<Place> placeList;
    private String PlacesInformation;

    public List<Place> getPlaceList(PlaceRepository placeRepository, double x, double y, int km) {
        Point point = new Point(x, y);
        Distance distance = new Distance(km, Metrics.KILOMETERS);
        List<Place> places = placeRepository.findByLocationNear(point, distance);
        return placeList;
    }

    public void setPlaceList(List<Place> placeList) {
        this.placeList = placeList;
    }

    public String getPlacesInformation(PlaceRepository placeRepository, TypeRepository typeRepository, double x, double y, int km) {
        JsonArray mainObject = new JsonArray(); // создаем главный объект
        List<String> category = Arrays.asList("Еда", "Развлечения", "Гостиницы", "Покупки", "Красота", "Здоровье");

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
            JsonArray array = new JsonArray();
            JsonObject rootObject = new JsonObject(); // создаем главный объект
            List<Type> bisenessTypes = typeRepository.findByCategory(category.get(f));
            rootObject.addProperty("name", category.get(f));
            rootObject.addProperty("types", 0);
            rootObject.addProperty("name", category.get(f));


            for (int i = 0; i < bisenessTypes.size(); i++) {
                JsonObject childObject = new JsonObject(); // создаем объект Type
                String type = bisenessTypes.get(i).getId();
                for (int q = 0; q < places.size(); q++) {
                    String place = places.get(q).getType().toString();
                    if (type.equals(place)) {
                        sumPlace = sumPlace + 1;
                        reit = reit + places.get(q).getRating();
                        price = price + places.get(q).getPrice();

                    }
                    childObject.addProperty("name", bisenessTypes.get(i).getName());
                    childObject.addProperty("col", sumPlace);
                    if (reit / sumPlace > 0) {
                        if (price / sumPlace > 0) {
                            childObject.addProperty("reit", reit / sumPlace);
                            childObject.addProperty("price", price / sumPlace);
                        } else {
                            childObject.addProperty("reit", reit / sumPlace);
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
                sumPlace = 0;
                reit = 0;
                price = 0;
                array.add(childObject);
            }

            rootObject.addProperty("totalCol", sumType);
            rootObject.add("types", array); // сохраняем дочерний объект в поле "type"
            sumType = 0;
            mainObject.add(rootObject);
            Gson gson = new Gson();
            PlacesInformation = gson.toJson(mainObject); // генерация json строки
        }
        return PlacesInformation;
    }

    public void setPlacesInformation(String placesInformation) {
        PlacesInformation = placesInformation;
    }

}