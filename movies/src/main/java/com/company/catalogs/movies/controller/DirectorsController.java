package com.company.catalogs.movies.controller;

import com.company.catalogs.movies.entity.DirectorEntity;
import com.company.catalogs.movies.service.DirectorService;
import io.swagger.annotations.*;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;
import java.util.List;

@Api(value = "directors controller", tags = {"access to director information"})
@RestController
@AllArgsConstructor
public class DirectorsController {
    private final DirectorService service;

    @ApiOperation(value = "get all directors",
            response = DirectorEntity[].class,
            httpMethod = "GET",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK")})
    @GetMapping(path = "/directors")
    public List<DirectorEntity> all(){
        return service.all();
    }
    
    @ApiOperation(value = "get a director",
            response = DirectorEntity.class,
            httpMethod = "GET",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Not Found")})
    @GetMapping(path = "/directors/{director}")
    public DirectorEntity one(
            @ApiParam(name = "director", required = true, value = "director identifier", defaultValue = "1", type = "path")
            @PathVariable(name = "director", required = true) Long director){
        return service.one(director);
    }

    @ApiOperation(value = "update a director",
            response = DirectorEntity.class,
            httpMethod = "PUT",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Success"),
            @ApiResponse(code = 404, message = "not found")
    })
    @PutMapping(path = "/directors")
    public ResponseEntity<?> update (
            @ApiParam(name = "directorEntity", required = true, value = "director entity", type = "body")
            @RequestBody DirectorEntity directorEntity) throws URISyntaxException {
        return service.update(directorEntity);
    }

    @ApiOperation(value = "create a director record",
            response = DirectorEntity.class,
            httpMethod = "POST",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "created")
    })
    @PostMapping(path = "/directors")
    public ResponseEntity<?> add (
            @ApiParam(name = "directorEntity", required = true, value = "director entity", type = "body")
            @RequestBody DirectorEntity directorEntity) throws URISyntaxException {
        return service.add(directorEntity);
    }

    @ApiOperation(value = "delete a director record",
            response = Void.class,
            httpMethod = "DELETE",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success"),
            @ApiResponse(code = 404, message = "not found")
    })
    @DeleteMapping(path = "/directors/{director}")
    public ResponseEntity<?> delete (
            @ApiParam(name = "director", required = true, value = "director identifier", type = "path")
            @PathVariable Long director){
        return service.delete(director);
    }
}

