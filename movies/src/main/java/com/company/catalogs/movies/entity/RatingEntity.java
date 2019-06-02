package com.company.catalogs.movies.entity;

import lombok.*;

import javax.persistence.*;

@Data
@Entity
@Getter
@Builder
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
