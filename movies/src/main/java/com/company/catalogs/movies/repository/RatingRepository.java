package com.company.catalogs.movies.repository;

import com.company.catalogs.movies.entity.RatingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RatingRepository extends JpaRepository<RatingEntity, Long> {

}
