package com.challenge.alkemy.service;

import com.challenge.alkemy.converter.GenreConverter;
import com.challenge.alkemy.dto.GenreDto;
import com.challenge.alkemy.entity.Genre;
import com.challenge.alkemy.exception.SpringException;
import com.challenge.alkemy.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Service
public class GenreServiceImpl implements GenreService{

    private final String ERROR_1 = "Error in server";

    @Autowired
    private GenreRepository genreRepository;
    @Autowired
    private GenreConverter genreConverter;

    @Override
    public String generateId() {
        String id;
        do{
            id = UUID.randomUUID().toString().replaceAll("[^0-9]", "").substring(0, 15);
        }while(genreRepository.existsById(id));

        return id;
    }

    @Override
    public GenreDto create(GenreDto genreDto) throws SpringException {
        try {
            String id = generateId();
            genreDto.setId(id);
            Genre genre = genreConverter.convertToEntity(genreDto);
            genreRepository.save(genre);

            return genreConverter.convertToDto(genre);
        }catch (SpringException e){
            throw e;
        }catch (Exception e){
            throw new SpringException(ERROR_1);
        }
    }

    @Override
    public GenreDto update(GenreDto genreDto) throws SpringException {
        try {
            Genre genre = genreRepository.findByIdAndDateOffIsNull(genreDto.getId());

            if (genre == null) {
                throw new SpringException("Genre not found", HttpStatus.NOT_FOUND);
            }
            genre = genreConverter.convertToEntity(genreDto);
            genreRepository.save(genre);

            return genreConverter.convertToDto(genre);

        }catch (SpringException e){
            throw e;
        }catch (Exception e){
            throw new SpringException(ERROR_1);
        }
    }

    @Override
    public Set<GenreDto> getGenres() throws SpringException {
        try{
            Set<GenreDto> genreDtos = new HashSet<>();

            for(Genre genre : genreRepository.findAllByDateOffIsNull()){
                genreDtos.add(genreConverter.convertToDto(genre));
            }
            return genreDtos;

        }catch (SpringException e){
            throw e;
        }catch (Exception e){
            throw new SpringException(ERROR_1);
        }
    }

    @Override
    public GenreDto getGenre(String id) throws SpringException {
        try{
            Genre genre = genreRepository.findByIdAndDateOffIsNull(id);

            if(genre == null){
                throw new SpringException("There is no genre in database", HttpStatus.NOT_FOUND);
            }

            return genreConverter.convertToDto(genre);

        }catch (Exception e){
            throw new SpringException(ERROR_1);
        }
    }

    @Override
    public void delete(String id) throws SpringException {
        try{
            Genre genre = genreRepository.findByIdAndDateOffIsNull(id);

            if(genre == null){
                throw new SpringException("There is no genre in database", HttpStatus.NOT_FOUND);
            }
            genre.setDateOff(new Date());
            genreRepository.save(genre);

        }catch (SpringException e){
            throw e;
        }catch (Exception e){
            throw new SpringException(ERROR_1);
        }
    }
}
