package com.example.BusinessMap.parser;

import com.example.BusinessMap.repositories.PlaceRepository;
import com.example.BusinessMap.repositories.TypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableScheduling
public class ParserApplication {
    private final PlaceRepository placeRepository;
    private final TypeRepository typeRepository;

    @Autowired
    public ParserApplication(final PlaceRepository placeRepository, final TypeRepository typeRepository) {
        this.placeRepository = placeRepository;
        this.typeRepository = typeRepository;
    }

    @Bean
    public Parser createBean()
    {
        return new Parser(placeRepository, typeRepository);
    }
}
