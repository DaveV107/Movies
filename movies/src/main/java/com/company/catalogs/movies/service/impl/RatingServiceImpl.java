package com.company.catalogs.movies.service.impl;

import com.company.catalogs.movies.entity.RatingEntity;
import com.company.catalogs.movies.exceptions.RatingException;
import com.company.catalogs.movies.repository.RatingRepository;
import com.company.catalogs.movies.service.RatingService;
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
public class RatingServiceImpl implements RatingService {

    private final RatingRepository repository;


    @Override
    public List<RatingEntity> all() {
        return repository.findAll();
    }

    @Override
    public RatingEntity one(Long rating) {
        return repository.findById(rating)
                .orElseThrow(() -> new RatingException("cannot find rating with id " + rating));
    }

    @Override
    public ResponseEntity<?> update(RatingEntity ratingEntity) throws URISyntaxException {
        repository.findById(ratingEntity.getRating())
                .orElseThrow(()->new RuntimeException("cannot find rating with id " + ratingEntity.getRating()));
        RatingEntity rating = repository.save(ratingEntity);
        return ResponseEntity
                .created(new URI(rating.getRating().toString()))
                .body(rating);
    }

    @Override
    public ResponseEntity<?> add(RatingEntity ratingEntity) throws URISyntaxException{
        RatingEntity rating = repository.save(ratingEntity);
        return ResponseEntity
                .created(new URI(rating.getRating().toString()))
                .body(rating);
    }

    @Override
    public ResponseEntity<?> delete(Long rating) {
        if(repository.existsById(rating)) {
            repository.deleteById(rating);
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
