package com.company.catalogs.movies.repository;

import com.company.catalogs.movies.entity.RatingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository
@RepositoryRestResource(exported = false)
public interface RatingRepository extends JpaRepository<RatingEntity, Long> {

}
