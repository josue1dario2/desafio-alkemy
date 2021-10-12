package com.challenge.alkemy.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Date;
import java.util.Set;

@Entity
@Setter
@Getter
public class Genre {
    @Id
    private String id;
    private String name;
    private String image;
    private Date dateOff;
    @OneToMany
    private Set<Movie> movies;
}
