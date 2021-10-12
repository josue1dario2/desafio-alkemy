package com.challenge.alkemy.converter;

import com.challenge.alkemy.dto.CharacterDto;
import com.challenge.alkemy.dto.MovieDto;
import com.challenge.alkemy.entity.Character;
import com.challenge.alkemy.entity.Movie;
import com.challenge.alkemy.exception.SpringException;
import com.challenge.alkemy.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class CharacterConverter {

    @Autowired
    private MovieRepository movieRepository;

    public Character convertToEntity(CharacterDto characterDto)throws SpringException {
        Character character = new Character();

        character.setId(characterDto.getId());
        character.setImage(characterDto.getImage());
        character.setName(characterDto.getName());
        character.setAge(characterDto.getAge());
        character.setWeight(characterDto.getWeight());
        character.setHistory(characterDto.getHistory());

        return character;
    }

    public CharacterDto convertToDto(Character character) throws SpringException{
        CharacterDto characterDto = new CharacterDto();

        characterDto.setId(character.getId());
        characterDto.setImage(character.getImage());
        characterDto.setName(character.getName());
        characterDto.setAge(character.getAge());
        characterDto.setWeight(character.getWeight());
        characterDto.setHistory(character.getHistory());

        if(!characterDto.getMovies().isEmpty()){
            getMovies(characterDto.getMovies());
        }

        return characterDto;
    }

    private Set<Movie> getMovies(Set<MovieDto> movieDtos){
        Set<Movie> movies = new HashSet<>();

        for(MovieDto movieDto : movieDtos ){
            if(movieRepository.existsByIdAndDateOffIsNull(movieDto.getId())){
                movies.add(movieRepository.findByIdAndDateOffIsNull(movieDto.getId()));
            }
        }
        return movies;
    }
}
