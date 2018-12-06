package com.example.BusinessMap.database.repositories;

import com.example.BusinessMap.database.entity.Type;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TypeRepository extends MongoRepository<Type, String> {
    List<Type> findByCategory(String category);
}
