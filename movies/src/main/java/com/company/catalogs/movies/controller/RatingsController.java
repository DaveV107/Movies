package com.company.catalogs.movies.controller;

import com.company.catalogs.movies.entity.RatingEntity;
import com.company.catalogs.movies.service.RatingService;
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

@Api(value = "ratings controller", tags = {"access to ratings information"})
@RestController
@AllArgsConstructor
public class RatingsController {

    private final RatingService service;
    
    @ApiOperation(value = "get all ratings",
            response = RatingEntity[].class,
            httpMethod = "GET",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK")})
    @GetMapping(path = "/ratings", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    List<RatingEntity> all(){
        return service.all();
    }
    
    @ApiOperation(value = "get a rating",
            response = RatingEntity.class,
            httpMethod = "GET",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Not Found")})
    @GetMapping(path = "/ratings/{rating}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    RatingEntity one(@PathVariable(name = "rating") Long rating){
        return service.one(rating);
    }

    @PutMapping(path = "/ratings")
    public ResponseEntity<?> update (@RequestBody RatingEntity ratingEntity) throws URISyntaxException {
        return service.update(ratingEntity);
    }

    @PostMapping(path = "/ratings")
    public ResponseEntity<?> add (@RequestBody RatingEntity ratingEntity) throws URISyntaxException {
        return service.add(ratingEntity);
    }

    @DeleteMapping(path = "/ratings/{rating}")
    public ResponseEntity<?> delete (@PathVariable Long rating){
        return service.delete(rating);
    }

}
