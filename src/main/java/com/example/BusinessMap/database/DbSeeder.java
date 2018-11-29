package com.example.BusinessMap.database;

import com.example.BusinessMap.database.entity.Type;
import com.example.BusinessMap.database.entity.Place;
import com.example.BusinessMap.database.repositories.PlaceRepository;
import com.example.BusinessMap.database.repositories.TypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.geo.Point;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DbSeeder implements CommandLineRunner {
    @Autowired
    private PlaceRepository placeRepository;
    @Autowired
    private TypeRepository typeRepository;

    public DbSeeder(PlaceRepository placeRepository) {
        this.placeRepository = placeRepository;
        this.typeRepository = typeRepository;
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
                "Friends Time",
                new Point(59.935939, 30.359860),
                new Type("Karaoke"),
                5,
                1000
        );

        //  List<Place> placesList = new ArrayList<>();
        //   placesList.add(i5);
        //   placeRepository.saveAll(placesList);

//        System.out.println(Querys.getOnly(placeRepository, bisenessTypeRepository));

        List<Type> types = typeRepository.findAll();

        System.out.println("######PLACES LIST AFTER ADDING######");
     //   System.out.println(Querys.getPlaces(placeRepository, typeRepository));
        System.out.println("###################################\n\n");
    }

}