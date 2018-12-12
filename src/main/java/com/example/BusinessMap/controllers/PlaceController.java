package com.example.BusinessMap.controllers;

import com.example.BusinessMap.repositories.PlaceRepository;
import com.example.BusinessMap.entity.Place;
import com.example.BusinessMap.repositories.TypeRepository;
import com.example.BusinessMap.services.PlaceService;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Metrics;
import org.springframework.data.geo.Point;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/places")
public class PlaceController {
    private PlaceRepository placeRepository;
    private TypeRepository typeRepository;
    @Inject
    PlaceService placeService;

    public PlaceController(PlaceRepository placeRepository, TypeRepository typeRepository) {
        this.placeRepository = placeRepository;
        this.typeRepository = typeRepository;
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
        String rez = PlaceService.getPlaces(placeRepository, typeRepository, x, y, km);
        return rez;
    }
}