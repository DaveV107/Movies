package com.company.catalogs.movies.controller;

import com.company.catalogs.movies.entity.MovieEntity;
import com.company.catalogs.movies.service.MovieService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;
import java.util.List;

@RestController
@AllArgsConstructor
public class MoviesController {

    private final MovieService service;

    @GetMapping(path = "/api/v1/movies", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    List<MovieEntity> all(){
        return service.all();
    }

    @GetMapping(path = "/api/v1/movies/{movie}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    MovieEntity one(@PathVariable(name = "movie") Long movie){
        return service.one(movie);
    }

    @GetMapping(path = "/api/v1/movies/director/{director}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    List<MovieEntity> findMoviesByDirector(@PathVariable(name = "director") Long director){
        return service.findMoviesByDirector(director);
    }

    @GetMapping(path = "/api/v1/movies/rating/{rating}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    List<MovieEntity> findMoviesAboveRating(@PathVariable(name = "rating") Long rating){
        return service.findMoviesAboveRating(rating);
    }

    @PutMapping(path = "/api/v1/movies")
    public ResponseEntity<?> update (@RequestBody MovieEntity movieEntity) throws URISyntaxException {
        return service.update(movieEntity);
    }

    @PostMapping(path = "/api/v1/movies")
    public ResponseEntity<?> add (@RequestBody MovieEntity movieEntity) throws URISyntaxException {
        return service.add(movieEntity);
    }

    @DeleteMapping(path = "/api/v1/movies/{movie}")
    public ResponseEntity<?> delete (@PathVariable Long movie){
        return service.delete(movie);
    }

}
