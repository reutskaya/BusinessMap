package com.example.BusinessMap.database.controllers;

import com.example.BusinessMap.database.Querys;
import com.example.BusinessMap.database.repositories.PlaceRepository;
import com.example.BusinessMap.database.entity.Place;
import com.example.BusinessMap.database.repositories.TypeRepository;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Metrics;
import org.springframework.data.geo.Point;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/places")
public class PlaceController {
    private PlaceRepository placeRepository;
    private TypeRepository typeRepository;

    public PlaceController(PlaceRepository placeRepository) {
        this.placeRepository = placeRepository;
    }

    @GetMapping("/all")
    public List<Place> getAll(){
        List<Place> places = this.placeRepository.findAll();
        return places;
    }

    @GetMapping("/{x}/{y}")
    public List<Place> getOnlyNeed(@PathVariable double x, @PathVariable double y){
        Point point = new Point(x, y);
        Distance distance = new Distance(1, Metrics.KILOMETERS);
        List<Place> places = this.placeRepository.findByLocationNear(point, distance);
        return places;
    }

    @GetMapping("/{x}/{y}/{km}")
    public String getPlaces(@PathVariable("x") double x, @PathVariable("y") double y, @PathVariable("km") int km){
        String rez = Querys.getPlaces(placeRepository, typeRepository, x, y, km);
        return rez;
    }
}
