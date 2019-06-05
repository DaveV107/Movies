package com.company.catalogs.movies.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@ApiModel(description = "Class representing a rating.")
@Data
@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "ratings")
public class RatingEntity {
    
    @ApiModelProperty(notes = "Unique identifier of the rating.", example = "1", required = true, position = 0)
    @NotNull
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="rating", nullable=false, updatable = false)
    private Long rating;
    
    @ApiModelProperty(notes = "rating code of the rating", example = "U", required = true, position = 1)
    @NotNull
    @Column(name = "ratingcode")
    private String ratingCode;
    
    @ApiModelProperty(notes = "rating value.", example = "Universal", required = true, position = 2)
    @NotNull
    @Column(name = "ratingvalue")
    private String ratingValue;
    
    @ApiModelProperty(notes = "rating description.", example = "Titles rated bbfc-uc have been classified suitable for all.", required = true, position = 3)
    @NotBlank
    @Column(name = "description")
    private String description;
}
