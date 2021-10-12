package com.challenge.alkemy.repository;

import com.challenge.alkemy.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Set;

@Repository
public interface MovieRepository extends JpaRepository<Movie,String> {

    boolean existsByIdAndDateOffIsNull(String id);

    Movie findByIdAndDateOffIsNull(String id);

    Movie findByTitleAndDateOffIsNull(String title);

    Set<Movie> findAllByDateOffIsNull();

    Set<Movie> findAllbyOrderByTitleAsc();

    Set<Movie> findAllbyOrderByTitleDesc();

    Set<Movie> findByGenreAndDateOffIsNull(String id);

}
