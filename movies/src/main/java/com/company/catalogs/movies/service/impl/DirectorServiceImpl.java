package com.company.catalogs.movies.service.impl;

import com.company.catalogs.movies.entity.DirectorEntity;
import com.company.catalogs.movies.repository.DirectorRepository;
import com.company.catalogs.movies.service.DirectorService;
import lombok.AllArgsConstructor;
import org.springframework.hateoas.VndErrors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@Service
@AllArgsConstructor
public class DirectorServiceImpl implements DirectorService {

    private final DirectorRepository repository;


    @Override
    public List<DirectorEntity> all() {
        return repository.findAll();
    }

    @Override
    public DirectorEntity one(Long director) {
        return repository.findById(director)
                .orElseThrow(()-> new RuntimeException("cannot find director with id " + director));
    }

    @Override
    public ResponseEntity<?> update(DirectorEntity directorEntity) throws URISyntaxException {
        if (repository.findById(directorEntity.getDirector())
                .orElseThrow(() -> new RuntimeException("cannot find director with id " + directorEntity.getDirector())) != null){
            DirectorEntity director = repository.save(directorEntity);
            return ResponseEntity
                    .created(new URI(director.getDirector().toString()))
                    .body(director);
        } else {
            return ResponseEntity
                    .notFound()
                    .build();
        }
        
    }

    @Override
    public ResponseEntity<?> add(DirectorEntity directorEntity) throws URISyntaxException{
        DirectorEntity director = repository.save(directorEntity);
        return ResponseEntity
                .created(new URI(director.getDirector().toString()))
                .body(director);
    }

    @Override
    public ResponseEntity<?> delete(Long director) {
        if(repository.existsById(director)) {
            repository.deleteById(director);
            return ResponseEntity
                    .noContent()
                    .build();
        } else {
            return ResponseEntity
                    .status(HttpStatus.GONE)
                    .body(new VndErrors.VndError("not allowed", "does not exist"));
        }
    }

}
