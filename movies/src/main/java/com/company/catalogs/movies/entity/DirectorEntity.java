package com.company.catalogs.movies.entity;

import lombok.*;

import javax.persistence.*;

@Data
@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "directors")
public class DirectorEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="director", nullable=false, updatable = false)
    private Long director;

    @Column(name = "firstname")
    private String firstName;

    @Column(name = "lastname")
    private String lastName;

}
