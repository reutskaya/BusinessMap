package com.example.BusinessMap.parser;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class StaticMap {
    static final  Map<String, String> mapOfTypes;
    static {
        Map<String, String> map = new HashMap<>();
        //еда
        map.put("Кафе", "kafe");
        map.put("Ресторан", "restoranyi");
        map.put("Бар", "baryi");
        map.put("Булочная", "bulochnyie");
        //здоровье
        map.put("Аптека", "apteki");
        map.put("Фитнес-клуб", "fitnes-kluby");
        map.put("Медицинский центр", "medicinskie-centry");
        //покупки
        map.put("Автомагазины", "avtomagaziny");
        map.put("Магазин сантехники", "magaziny-santekhniki");
        map.put("Магазин электроники", "magaziny-elektroniki");
        //красота
        map.put("Салон красоты", "salony-krasoty");
        map.put("Парикмахерская", "parikmaherskie");
        //развлечения
        map.put("Парк аттракционов", "parki-attraktsionov");
        map.put("Зоопарк", "zooparki");
        //гостиницы
        map.put("Хостел", "hostelyi");
        map.put("Гостиница", "gostinicy");
        mapOfTypes = Collections.unmodifiableMap(map);
    }

   /* public static void addTypesToDB(){
        mapOfTypes.forEach((key, val) -> typeRepository.save(new Type(key)));
        List


        Type newTypeы = new Type(type);
        typeRepository.save(newType);
    }*/
}
