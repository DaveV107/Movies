package com.company.catalogs.movies.service;

import com.company.catalogs.movies.entity.RatingEntity;

import java.util.List;

public interface RatingService {


    List<RatingEntity> all();

    RatingEntity one(Long rating);

}

