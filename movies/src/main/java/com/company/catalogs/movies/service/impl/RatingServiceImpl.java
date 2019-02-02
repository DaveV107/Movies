package com.company.catalogs.movies.service.impl;

import com.company.catalogs.movies.entity.RatingEntity;
import com.company.catalogs.movies.repository.RatingRepository;
import com.company.catalogs.movies.service.RatingService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

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
                .orElseThrow(() -> new RuntimeException("cannot find rating with id " + rating));
    }
}
