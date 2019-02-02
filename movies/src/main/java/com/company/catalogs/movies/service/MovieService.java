package com.company.catalogs.movies.service;

import com.company.catalogs.movies.entity.MovieEntity;
import org.springframework.http.ResponseEntity;

import java.net.URISyntaxException;
import java.util.List;

public interface MovieService {


    List<MovieEntity> all();

    MovieEntity one(Long movie);

    List<MovieEntity> findMoviesByDirector(Long director);

    List<MovieEntity> findMoviesAboveRating(Long rating);

    ResponseEntity<?> update(MovieEntity movieEntity) throws URISyntaxException;

    ResponseEntity<?> add(MovieEntity movieEntity) throws URISyntaxException;

    ResponseEntity<?> delete(Long movie);

}
