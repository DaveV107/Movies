package com.company.catalogs.movies.controller;

import com.company.catalogs.movies.entity.DirectorEntity;
import com.company.catalogs.movies.service.DirectorService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;
import java.util.List;

@RestController
@AllArgsConstructor
public class DirectorsController {
    private final DirectorService service;

    @GetMapping(path = "/api/v1/directors", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<DirectorEntity> all(){
        return service.all();
    }

    @GetMapping(path = "/api/v1/directors/{director}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public DirectorEntity one(@PathVariable(name = "director") Long director){
        return service.one(director);
    }

    @PutMapping(path = "/api/v1/directors")
    public ResponseEntity<?> update (@RequestBody DirectorEntity directorEntity) throws URISyntaxException {
        return service.update(directorEntity);
    }

    @PostMapping(path = "/api/v1/directors")
    public ResponseEntity<?> add (@RequestBody DirectorEntity directorEntity) throws URISyntaxException {
        return service.add(directorEntity);
    }

    @DeleteMapping(path = "/api/v1/directors/{director}")
    public ResponseEntity<?> delete (@PathVariable Long director){
        return service.delete(director);
    }
}
