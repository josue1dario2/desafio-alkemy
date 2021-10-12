package com.challenge.alkemy.repository;

import com.challenge.alkemy.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface GenreRepository extends JpaRepository<Genre,String> {

    Genre findByIdAndDateOffIsNull(String genreId);

    Set<Genre> findAllByDateOffIsNull();

}
