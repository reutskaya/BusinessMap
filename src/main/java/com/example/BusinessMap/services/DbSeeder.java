package com.example.BusinessMap.services;

import com.example.BusinessMap.entity.Type;
import com.example.BusinessMap.entity.Place;
import com.example.BusinessMap.repositories.PlaceRepository;
import com.example.BusinessMap.repositories.TypeRepository;
import com.example.BusinessMap.parser.StaticMap;
import org.bson.types.ObjectId;
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
        //this.placeRepository.deleteAll();
        this.typeRepository.deleteAll();
        typeRepository.saveAll(typesToAdd());

        Place i5 = new Place(
                "Friends Time",
                new Point(59.935939, 30.359860),
                new ObjectId("5c097053ad2b3d17fc2d81eb"),
                5,
                1000
        );

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

    private static List<Type> typesToAdd() {
        List<Type> typeListToAdd = new ArrayList<>();
        StaticMap.mapOfTypes.forEach((key, value) -> {
            switch (key) {
                case "Cafe":
                case "Ресторан":
                case "Бар":
                case "Булочная": {
                    typeListToAdd.add(new Type(key, "Еда"));
                    break;
                }
                case "Аптека":
                case "Фитнес-клуб":
                case "Медицинский центр": {
                    typeListToAdd.add(new Type(key, "Здоровье"));
                    break;
                }
                case "Автомагазин":
                case "Магазин сантехники":
                case "Магазин электроники": {
                    typeListToAdd.add(new Type(key, "Покупки"));
                    break;
                }
                case "Салон красоты":
                case "Парикмахерская": {
                    typeListToAdd.add(new Type(key, "Красота"));
                    break;
                }
                case "Парк аттракционов":
                case "Зоопарк": {
                    typeListToAdd.add(new Type(key, "Развлечения"));
                    break;
                }
                case "Хостел":
                case "Гостиница": {
                    typeListToAdd.add(new Type(key, "Гостиницы"));
                    break;
                }
                default:
            }
        });
        return typeListToAdd;
    }

}
