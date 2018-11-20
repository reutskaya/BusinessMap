package com.example.BusinessMap.database;

import com.example.BusinessMap.database.entity.BisenessType;
import com.example.BusinessMap.database.entity.Place;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.geo.Point;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DbSeeder implements CommandLineRunner {
    private final PlaceRepository placeRepository;

    @Autowired
    public DbSeeder(PlaceRepository placeRepository) {
        this.placeRepository = placeRepository;
    }

    @Override
    public void run(String... args) throws Exception {

     /*   System.out.println("######PLACES LIST AT BEGINNING######");
        System.out.println(placeRepository.findAll());
        System.out.println("###################################\n\n");

        // drop all places
        System.out.println("######NOW I DELETE ALL PLACES######\n\n");
        this.placeRepository.deleteAll(); */

        System.out.println("######ADDING 2 PLACES######");

        Place i5 = new Place(
                "Голубая Устрица",
                new Point(59.931920, 30.327925),
                new BisenessType("Karaoke"),
                999,
                999
        );

        List<Place> placesList = new ArrayList<>();
        placesList.add(i5);

        System.out.println(Querys.getOnlyNeed(placeRepository).toString());
        //    placeRepository.saveAll(placesList);


     /*   System.out.println("######PLACES LIST AFTER ADDING######");
        System.out.println(placeRepository.findAll());
        System.out.println("###################################\n\n");   */ }

}

