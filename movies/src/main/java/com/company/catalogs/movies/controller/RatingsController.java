package com.company.catalogs.movies.controller;

import com.company.catalogs.movies.entity.RatingEntity;
import com.company.catalogs.movies.service.RatingService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;
import java.util.List;

@RestController
@AllArgsConstructor
public class RatingsController {

    private final RatingService service;

    @GetMapping(path = "/api/v1/ratings", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    List<RatingEntity> all(){
        return service.all();
    }

    @GetMapping(path = "/api/v1/ratings/{rating}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    RatingEntity one(@PathVariable(name = "rating") Long rating){
        return service.one(rating);
    }

    @PutMapping(path = "/api/v1/ratings")
    public ResponseEntity<?> update (@RequestBody RatingEntity ratingEntity) throws URISyntaxException {
        return service.update(ratingEntity);
    }

    @PostMapping(path = "/api/v1/ratings")
    public ResponseEntity<?> add (@RequestBody RatingEntity ratingEntity) throws URISyntaxException {
        return service.add(ratingEntity);
    }

    @DeleteMapping(path = "/api/v1/ratings/{rating}")
    public ResponseEntity<?> delete (@PathVariable Long rating){
        return service.delete(rating);
    }

}
