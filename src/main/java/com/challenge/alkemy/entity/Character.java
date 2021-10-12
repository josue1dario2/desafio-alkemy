package com.challenge.alkemy.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.Date;
import java.util.Set;
@Entity
@Setter
@Getter
@NoArgsConstructor
public class Character {
    @Id
    private String id;
    private String name;
    private Integer age;
    private Double weight;
    private String history;
    private String image;
    private Date dateOff;

    @ManyToMany
    private Set<Movie> movies;
}
