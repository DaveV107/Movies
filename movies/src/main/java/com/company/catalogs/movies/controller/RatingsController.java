package com.company.catalogs.movies.controller;

import com.company.catalogs.movies.entity.RatingEntity;
import com.company.catalogs.movies.service.RatingService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class RatingsController {


    private final RatingService service;

    private final static Logger LOGGER = LoggerFactory.getLogger(RatingsController.class);

    @GetMapping(path = "/api/v1/ratings", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    List<RatingEntity> all(){
        return service.all();
    }

    @GetMapping(path = "/api/v1/ratings/{rating}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    RatingEntity one(@PathVariable(name = "rating") Long rating){
        return service.one(rating);
    }

}
