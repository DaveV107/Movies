package com.company.catalogs.movies.service.impl;

import com.company.catalogs.movies.entity.MovieEntity;
import com.company.catalogs.movies.exceptions.MovieException;
import com.company.catalogs.movies.repository.MovieRepository;
import com.company.catalogs.movies.service.MovieService;
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
public class MovieServiceImpl implements MovieService {

    private final MovieRepository repository;

    @Override
    public List<MovieEntity> all() {
        return repository.findAll();
    }

    @Override
    public MovieEntity one(Long movie) {
        return repository.findById(movie)
                .orElseThrow(()-> new MovieException("cannot find movie by that id " + movie));
    }

    @Override
    public List<MovieEntity> findMoviesByDirector(Long director) {
        return repository.findMoviesByDirector(director);
    }

    @Override
    public List<MovieEntity> findMoviesAboveRating(Long rating) {
        return repository.findMoviesAboveRating(rating);
    }

    @Override
    public ResponseEntity<?> update(MovieEntity movieEntity) throws URISyntaxException {
        repository.findById(movieEntity.getMovie())
                .orElseThrow(()->new RuntimeException("cannot find director with id " + movieEntity.getMovie()));
        MovieEntity movie = repository.save(movieEntity);
        return ResponseEntity
                .created(new URI(movie.getMovie().toString()))
                .body(movie);
    }

    @Override
    public ResponseEntity<?> add(MovieEntity movieEntity) throws URISyntaxException{
        MovieEntity movie = repository.save(movieEntity);
        return ResponseEntity
                .created(new URI(movie.getMovie().toString()))
                .body(movie);
    }

    @Override
    public ResponseEntity<?> delete(Long movie) {
        if(repository.existsById(movie)) {
            repository.deleteById(movie);
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
