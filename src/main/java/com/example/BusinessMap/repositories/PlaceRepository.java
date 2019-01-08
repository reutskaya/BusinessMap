package com.example.BusinessMap.repositories;


import com.example.BusinessMap.entity.Place;
import com.example.BusinessMap.entity.Type;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlaceRepository extends MongoRepository<Place, String> {
    List<Place> findByLocationNear(Point location, Distance distance);
}