package com.challenge.alkemy.converter;

import com.challenge.alkemy.dto.GenreDto;
import com.challenge.alkemy.entity.Genre;
import com.challenge.alkemy.exception.SpringException;
import org.springframework.stereotype.Component;

@Component
public class GenreConverter {

    public GenreDto convertToDto(Genre genre) throws SpringException {
        GenreDto genreDto = new GenreDto();

        genreDto.setName(genre.getName());
        genreDto.setImage(genre.getImage());

        return genreDto;
    }

    public Genre convertToEntity(GenreDto genreDto) throws SpringException{
        Genre genre = new Genre();

        genre.setId(genreDto.getId());
        genre.setName(genreDto.getName());
        genre.setImage(genreDto.getImage());

        return genre;
    }

}
