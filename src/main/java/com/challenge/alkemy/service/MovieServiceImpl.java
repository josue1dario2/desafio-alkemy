package com.challenge.alkemy.service;

import com.challenge.alkemy.converter.MovieConverter;
import com.challenge.alkemy.dto.MovieDto;
import com.challenge.alkemy.entity.Character;
import com.challenge.alkemy.entity.Genre;
import com.challenge.alkemy.entity.Movie;
import com.challenge.alkemy.exception.SpringException;
import com.challenge.alkemy.repository.CharacterRepository;
import com.challenge.alkemy.repository.GenreRepository;
import com.challenge.alkemy.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

@Service
public class MovieServiceImpl implements MovieService{

    @Autowired
    MovieRepository movieRepository;

    @Autowired
    CharacterRepository characterRepository;

    @Autowired
    GenreRepository genreRepository;

    @Autowired
    MovieConverter movieConverter;

    private final String ERROR_1 = "Error in server";

    @Override
    public String generateId() {
       String id;
       do{
           id = UUID.randomUUID().toString().replaceAll("[^0-9]", "").substring(0,15);
       }while(movieRepository.existsById(id));
       return id;
    }


    @Override
    public MovieDto create(MovieDto movieDto) throws SpringException {
        String id = generateId();
        movieDto.setId(id);

        Movie movie = movieConverter.convertToEntity(movieDto);
        movieRepository.save(movie);

        return movieConverter.converterDto(movie);

    }

    @Override
    public MovieDto update(MovieDto movieDto, String idGenre, MultipartFile image, Set<String> listIdCharacters) throws SpringException {
        try{
            Movie movie = movieRepository.findByTitleAndDateOffIsNull(movieDto.getTitle());

            if(movie == null){
                throw new SpringException("Movie not found");
            }
            movie.setTitle(movieDto.getTitle());
            movie.setCreationDate(movieDto.getCreationDate());
            movie.setRating(movieDto.getRating());
            movie.setImage(movieDto.getImage());

            for(String id : listIdCharacters){
                Character character = characterRepository.findByIdAndDateOffIsNull(id);

                if(character == null){
                    throw new SpringException("Character not found");
                }
                movie.getCharacters().add(character);
            }

            Genre genre = genreRepository.findByIdAndDateOffIsNull(idGenre);
            if(genre == null){
                throw new SpringException("Genre not found");
            }
            movie.setGenre(genre);
            movieRepository.save(movie);
            return movieConverter.converterDto(movie);

        }catch (SpringException e){
            throw e;
        }catch (Exception e){
            throw new SpringException(ERROR_1);
        }

    }

    @Override
    public Set<MovieDto> getMovies() throws SpringException {
        try{
            Set<Movie> movies = movieRepository.findAllByDateOffIsNull();
            Set<MovieDto> moviesDto = new HashSet<>();

            if(movies.isEmpty()){
                throw new SpringException("There are not movies in the database");
            }
            for(Movie movie : movies){
                moviesDto.add(movieConverter.converterDto(movie));
            }
            return moviesDto;
        }catch (SpringException e){
            throw e;
        }catch (Exception e){
            throw new SpringException(ERROR_1);
        }
    }

    @Override
    public MovieDto getMovie(String id) throws SpringException {
        try{
            Movie movie = movieRepository.findByIdAndDateOffIsNull(id);
            if(movie == null){
                throw new SpringException("The movie is not in the database");
            }
            return movieConverter.converterDto(movie);
        }catch (SpringException e){
            throw e;
        }catch (Exception e){
            throw new SpringException(ERROR_1);
        }
    }

    @Override
    public void delete(String id) throws SpringException {
        try{
            Movie movie = movieRepository.findByIdAndDateOffIsNull(id);

            if(movie == null){
                throw new SpringException("Movie not found");
            }
            movie.setDateOff(new Date());
            movieRepository.save(movie);

        }catch (SpringException e){
            throw e;
        }catch (Exception e){
            throw new SpringException(ERROR_1);
        }
    }


    @Override
    public MovieDto getMovieByTitle(String title) throws SpringException {
       try{
           Movie movie = movieRepository.findByTitleAndDateOffIsNull(title);

           if(movie == null){
               throw new SpringException("The movie is not in the database");
           }
           return movieConverter.converterDto(movie);
       }catch (SpringException e){
           throw e;
       }catch (Exception e){
           throw new SpringException(ERROR_1);
       }
    }

    @Override
    public Set<MovieDto> getMovieByGenre(String id) throws SpringException {
        try {
            Genre genre = genreRepository.findByIdAndDateOffIsNull(id);

            if (genre == null) {
                throw new SpringException("Genre not found in the database");
            }
            Set<Movie> movies = new HashSet<>();

            for (Movie movie : genre.getMovies()) {
                movies.add(movie);
            }
            Set<MovieDto> moviesDto = new HashSet<>();
            for (Movie movie : movies) {
                moviesDto.add(movieConverter.converterDto(movie));
            }
            return moviesDto;
        }catch (SpringException e){
            throw e;
        }catch (Exception e){
            throw new SpringException(ERROR_1);
        }
    }

    @Override
    public List<MovieDto> getMovieOrderByTitle(String order) throws SpringException {
        try{
            List<Movie> movies = new ArrayList<>();

            if(order.equalsIgnoreCase("asc")){
                movies.addAll(movieRepository.findAllbyOrderByTitleAsc());

            }else if(order.equalsIgnoreCase("desc")){
                movies.addAll(movieRepository.findAllbyOrderByTitleDesc());

            }else{
                throw new SpringException("Wrong value");
            }
            List<MovieDto> moviesDto = new ArrayList<>();

            for(Movie movie : movies){
                moviesDto.add(movieConverter.converterDto(movie));
            }
            return moviesDto;
        }catch (SpringException e){
            throw e;
        }catch (Exception e){
            throw new SpringException(ERROR_1);
        }
    }
}
