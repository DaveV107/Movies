package com.company.catalogs.movies.service;

import com.company.catalogs.movies.entity.DirectorEntity;
import org.springframework.http.ResponseEntity;

import java.net.URISyntaxException;
import java.util.List;

public interface DirectorService {

    List<DirectorEntity> all();

    DirectorEntity one(Long director);

    ResponseEntity<?> update(DirectorEntity directorEntity) throws URISyntaxException;

    ResponseEntity<?> add(DirectorEntity directorEntity) throws URISyntaxException;

    ResponseEntity<?> delete(Long director);

}
