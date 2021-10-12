package com.challenge.alkemy.converter;

import com.challenge.alkemy.dto.MovieDto;
import com.challenge.alkemy.entity.Movie;
import com.challenge.alkemy.exception.SpringException;
import com.challenge.alkemy.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class MovieConverter {

    @Autowired
    GenreConverter genreConverter;

    @Autowired
    GenreRepository genreRepository;

    public MovieDto converterDto(Movie movie)throws SpringException{
        MovieDto movieDto = new MovieDto();

        movieDto.setTitle(movie.getTitle());
        movieDto.setCreationDate(movie.getCreationDate());
        movieDto.setImage(movie.getImage());
        movieDto.setRating(movie.getRating());
        movieDto.setGenreId(movie.getGenre().getId());

        return movieDto;
    }

    public Movie convertToEntity(MovieDto movieDto)throws SpringException{
        Movie movie = new Movie();

        movie.setId(movieDto.getId());
        movie.setTitle(movieDto.getTitle());
        movie.setCreationDate(movieDto.getCreationDate());
        movie.setImage(movieDto.getImage());
        movie.setRating(movieDto.getRating());
        movie.setGenre(genreRepository.findByIdAndDateOffIsNull(movieDto.getGenreId()));

        if(movie.getGenre() == null){
            throw new SpringException("Genre not found");
        }
        return movie;
    }

}
