package com.example.BusinessMap.database.controllers;

import com.example.BusinessMap.database.entity.Type;
import com.example.BusinessMap.database.repositories.TypeRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/types")
public class TypeController {
    private TypeRepository typeRepository;

    public TypeController(TypeRepository typeRepository) {
        this.typeRepository = typeRepository;
    }

    @GetMapping("/all")
    public List<Type> getAll(){
        List<Type> typeList = this.typeRepository.findAll();
        return typeList;
    }
}