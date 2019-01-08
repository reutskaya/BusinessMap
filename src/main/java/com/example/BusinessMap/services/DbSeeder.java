package com.example.BusinessMap.services;

import com.example.BusinessMap.entity.Type;
import com.example.BusinessMap.parser.StaticMap;
import com.example.BusinessMap.repositories.PlaceRepository;
import com.example.BusinessMap.repositories.TypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DbSeeder implements CommandLineRunner {
    @Autowired
    private PlaceRepository placeRepository;
    @Autowired
    private TypeRepository typeRepository;


    PlaceService placeService = new PlaceService(placeRepository,typeRepository);

    public DbSeeder(PlaceRepository placeRepository, TypeRepository typeRepository, PlaceService placeService) {
        this.placeRepository = placeRepository;
        this.typeRepository = typeRepository;
    }


    @Override
    public void run(String... args) throws Exception {
        // to drop
        //this.placeRepository.deleteAll();
        //this.typeRepository.deleteAll();

        //init types
        //typeRepository.saveAll(typesToAdd());

        // to add place/places
        //List<Place> placesList = new ArrayList<>();
        //placesList.add(new Place(...));
        //placeRepository.saveAll(placesList);
    }

    private static List<Type> typesToAdd() {
        List<Type> typeListToAdd = new ArrayList<>();
        StaticMap.mapOfTypes.forEach((key, value) -> {
            switch (key) {
                case "Кафе":
                case "Ресторан":
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
