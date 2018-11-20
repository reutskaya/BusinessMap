package com.example.BusinessMap.database;

import com.example.BusinessMap.database.entity.Place;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Metrics;
import org.springframework.data.geo.Point;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@RequestMapping("/places")
public class PlaceController {
    private PlaceRepository placeRepository;

    public PlaceController(PlaceRepository placeRepository) {
        this.placeRepository = placeRepository;
    }

    @GetMapping("/all")
    public List<Place> getAll(){
        List<Place> places = this.placeRepository.findAll();
        return places;
    }

    @GetMapping("/onlyneed")
    public List<Place> getOnlyNeed(){
        Point point = new Point(59.932229, 30.330791);
        Distance distance = new Distance(1, Metrics.KILOMETERS);
        List<Place> places = this.placeRepository.findByLocationNear(point, distance);
        return places;
    }
}
