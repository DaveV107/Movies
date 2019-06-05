package com.company.catalogs.movies.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@ApiModel(description = "Class representing a director.")
@Data
@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "directors")
public class DirectorEntity {
    
    @ApiModelProperty(notes = "Unique identifier of the director.", example = "1", required = true, position = 0)
    @NotNull
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="director", nullable=false, updatable = false)
    private Long director;
    
    @ApiModelProperty(notes = "first name", example = "John", required = true, position = 1)
    @NotEmpty
    @Column(name = "firstname")
    private String firstName;
    
    @ApiModelProperty(notes = "last name", example = "Doe", required = true, position = 2)
    @NotEmpty
    @Column(name = "lastname")
    private String lastName;

}
