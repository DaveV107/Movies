package com.company.catalogs.movies.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "ratings")
public class RatingEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="rating", nullable=false, updatable = false)
    private Long rating;

    @Column(name = "ratingcode")
    private String ratingCode;

    @Column(name = "ratingvalue")
    private String ratingValue;

    @Column(name = "description")
    private String description;
}
