package com.company.catalogs.movies.repository;

import com.company.catalogs.movies.entity.MovieEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<MovieEntity, Long> {

    @Query(value = "select movies from MovieEntity movies join DirectorEntity directors on movies.director = directors.director where directors.director = :director" )
    List<MovieEntity> findMoviesByDirector(@Param("director") Long director);

    @Query(value = "select movies from MovieEntity movies join RatingEntity ratings on movies.rating = ratings.rating where ratings.rating > :rating" )
    List<MovieEntity> findMoviesAboveRating(@Param("rating") Long rating);

}

