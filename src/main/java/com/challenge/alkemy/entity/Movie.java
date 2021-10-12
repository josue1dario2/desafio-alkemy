package com.challenge.alkemy.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.util.Date;
import java.util.Set;
@Entity
@Setter
@Getter
public class Movie {
    @Id
    private String id;
    private String title;
    private Date creationDate;
    private Integer rating;
    private String image;
    private Date dateOff;
    @ManyToMany
    private Set<Character> characters;
    @ManyToOne
    private Genre genre;
}
