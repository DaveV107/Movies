package com.company.catalogs.movies.service;

import com.company.catalogs.movies.entity.RatingEntity;
import org.springframework.http.ResponseEntity;

import java.net.URISyntaxException;
import java.util.List;

public interface RatingService {


    List<RatingEntity> all();

    RatingEntity one(Long rating);

    ResponseEntity<?> update(RatingEntity ratingEntity) throws URISyntaxException;

    ResponseEntity<?> add(RatingEntity ratingEntity) throws URISyntaxException;

    ResponseEntity<?> delete(Long rating);
}

