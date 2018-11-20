package com.example.BusinessMap.database;


import com.example.BusinessMap.database.entity.Place;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlaceRepository extends MongoRepository<Place, String> {
    // { 'location' : { '$near' : [point.x, point.y], '$maxDistance' : distance}}
    List<Place> findByLocationNear(Point location, Distance distance);
}


