package com.company.catalogs.movies.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@ApiModel(description = "Class representing a movie.")
@Data
@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "movies")
public class MovieEntity {
    
    @ApiModelProperty(notes = "Unique identifier of the movie.", example = "1", required = true, position = 0)
    @NotNull
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="movie", nullable=false, updatable = false)
    private Long movie;
    
    @ApiModelProperty(notes = "rating for the movie.", example = "1", required = true, position = 1)
    @NotNull
    @ManyToOne
    @JoinColumn(name = "rating")
    private RatingEntity rating;
    
    @ApiModelProperty(notes = "director of the movie.", example = "1", required = true, position = 2)
    @NotNull
    @ManyToOne
    @JoinColumn(name = "director")
    private DirectorEntity director;
    
    @ApiModelProperty(notes = "name of the movie.", example = "Spiderman", required = true, position = 3)
    @NotNull
    @Column(name = "name")
    private String name;
    
    @ApiModelProperty(notes = "description of the movie.", example = "Radioactive spider bites Peter Parker during a school trip", required = true, position = 4)
    @NotEmpty
    @Column(name = "description")
    private String description;

}
