package com.example.BusinessMap.database;

import com.example.BusinessMap.database.entity.Place;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Metrics;
import org.springframework.data.geo.Point;

import java.util.List;

public class Querys {
    //private  ;

//    public Querys(PlaceRepository placeRepository) {
 //       this.placeRepository = placeRepository;
 //   }

    public static List<Place> getOnlyNeed(PlaceRepository placeRepository) {
        Point point = new Point(59.932229, 30.330791);
        Distance distance = new Distance(1, Metrics.KILOMETERS);
        List<Place> places = placeRepository.findByLocationNear(point, distance);
        return places;
    }

    public Querys() {

    }
}
