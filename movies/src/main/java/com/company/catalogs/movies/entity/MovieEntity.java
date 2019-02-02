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
@Table(name = "movies")
public class MovieEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="movie", nullable=false, updatable = false)
    private Long movie;

    @ManyToOne
    @JoinColumn(name = "rating")
    private RatingEntity rating;

    @ManyToOne
    @JoinColumn(name = "director")
    private DirectorEntity director;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

}
