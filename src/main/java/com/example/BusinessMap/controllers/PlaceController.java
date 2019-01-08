package com.example.BusinessMap.controllers;

import com.example.BusinessMap.entity.Place;
import com.example.BusinessMap.services.PlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/places")
public class PlaceController {

    @Autowired
    PlaceService placeService;


    @GetMapping("/all")
    public List<Place> getAll(){
        List<Place> places = placeService.getAllPlaces();
        return places;
    }


    @GetMapping("/{x}/{y}/{km}")
    public String getPlacesInformation(@PathVariable("x") double x, @PathVariable("y") double y, @PathVariable("km") double km){
        String rez = placeService.getPlacesInformation(x, y, km);
        return rez;
    }
}