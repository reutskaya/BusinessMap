package com.example.BusinessMap.database;

import com.example.BusinessMap.database.entity.Place;
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
}
