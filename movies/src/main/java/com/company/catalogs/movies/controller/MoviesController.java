package com.company.catalogs.movies.controller;

import com.company.catalogs.movies.entity.MovieEntity;
import com.company.catalogs.movies.service.MovieService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;
import java.util.List;

@Api(value = "movies controller", tags = {"access to movie information"})
@RestController
@AllArgsConstructor
public class MoviesController {

    private final MovieService service;
    
    @ApiOperation(value = "get all movies",
            response = MovieEntity[].class,
            httpMethod = "GET",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK")})
    @GetMapping(path = "/movies", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    List<MovieEntity> all(){
        return service.all();
    }
    
    @ApiOperation(value = "get a movie",
            response = MovieEntity.class,
            httpMethod = "GET",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Not Found")})
    @GetMapping(path = "/movies/{movie}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    MovieEntity one(@PathVariable(name = "movie") Long movie){
        return service.one(movie);
    }

    @GetMapping(path = "/movies/director/{director}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    List<MovieEntity> findMoviesByDirector(@PathVariable(name = "director") Long director){
        return service.findMoviesByDirector(director);
    }

    @GetMapping(path = "/movies/rating/{rating}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    List<MovieEntity> findMoviesAboveRating(@PathVariable(name = "rating") Long rating){
        return service.findMoviesAboveRating(rating);
    }

    @PutMapping(path = "/movies")
    public ResponseEntity<?> update (@RequestBody MovieEntity movieEntity) throws URISyntaxException {
        return service.update(movieEntity);
    }

    @PostMapping(path = "/movies")
    public ResponseEntity<?> add (@RequestBody MovieEntity movieEntity) throws URISyntaxException {
        return service.add(movieEntity);
    }

    @DeleteMapping(path = "/movies/{movie}")
    public ResponseEntity<?> delete (@PathVariable Long movie){
        return service.delete(movie);
    }

}
