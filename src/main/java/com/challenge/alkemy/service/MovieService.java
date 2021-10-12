package com.challenge.alkemy.service;

import com.challenge.alkemy.dto.MovieDto;
import com.challenge.alkemy.exception.SpringException;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Set;

public interface MovieService {

    public String generateId();

    public MovieDto create(MovieDto movieDto)throws SpringException;

    public MovieDto update(MovieDto movieDto, String idGenre, MultipartFile image, Set<String> listIdCharacters) throws SpringException;

    public Set<MovieDto> getMovies()throws SpringException;

    public MovieDto getMovie(String id) throws SpringException;

    public void delete(String id) throws SpringException;

    public MovieDto getMovieByTitle(String title) throws SpringException;

    public Set<MovieDto> getMovieByGenre(String id) throws SpringException;

    public List<MovieDto> getMovieOrderByTitle(String order) throws SpringException;
}
