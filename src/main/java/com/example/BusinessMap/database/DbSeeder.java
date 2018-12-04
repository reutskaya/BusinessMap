package com.example.BusinessMap.database;

import com.example.BusinessMap.database.entity.Type;
import com.example.BusinessMap.database.entity.Place;
import com.example.BusinessMap.database.repositories.PlaceRepository;
import com.example.BusinessMap.database.repositories.TypeRepository;
import com.example.BusinessMap.parser.Parser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.geo.Point;
import org.springframework.stereotype.Component;

@Component
public class DbSeeder implements CommandLineRunner {
    @Autowired
    private PlaceRepository placeRepository;
    @Autowired
    private TypeRepository typeRepository;

    public DbSeeder(PlaceRepository placeRepository, TypeRepository typeRepository) {
        this.placeRepository = placeRepository;
        this.typeRepository = typeRepository;
    }

    @Override
    public void run(String... args) throws Exception {

     /*   System.out.println("######PLACES LIST AT BEGINNING######");
        System.out.println(placeRepository.findAll());
        System.out.println("###################################\n\n");*/

        // drop all places
        System.out.println("######NOW I DELETE ALL PLACES######\n\n");
        this.placeRepository.deleteAll();
        this.typeRepository.deleteAll();

        System.out.println("######ADDING 2 PLACES######");

        Place i5 = new Place(
                "Friends Time",
                new Point(59.935939, 30.359860),
                new Type("Karaoke", "Entertaiment"),
                5,
                1000
        );

        //добавляем все типы в базу. делаем это только один раз!


        //  List<Place> placesList = new ArrayList<>();
        //   placesList.add(i5);
        //   placeRepository.saveAll(placesList);

//        System.out.println(Querys.getOnly(placeRepository, bisenessTypeRepository));

        //Parser parser = new Parser(placeRepository,typeRepository);
        //parser.parse();

        //List<Type> types = typeRepository.findAll();

        //System.out.println("######PLACES LIST AFTER ADDING######");
        //   System.out.println(Querys.getPlaces(placeRepository, typeRepository));
        //System.out.println("###################################\n\n");
    }

}
