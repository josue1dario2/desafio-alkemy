package com.challenge.alkemy.service;

import com.challenge.alkemy.dto.GenreDto;
import com.challenge.alkemy.exception.SpringException;

import java.util.Set;

public interface GenreService {

    public String generateId();

    public GenreDto create(GenreDto genreDto) throws SpringException;

    public GenreDto update(GenreDto genreDto) throws SpringException;

    public Set<GenreDto> getGenres() throws SpringException;

    public GenreDto getGenre(String id) throws SpringException;

    public void delete(String id) throws SpringException;
}
