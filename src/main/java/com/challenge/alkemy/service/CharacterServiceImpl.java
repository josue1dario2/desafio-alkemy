package com.challenge.alkemy.service;

import com.challenge.alkemy.converter.CharacterConverter;
import com.challenge.alkemy.dto.CharacterDto;
import com.challenge.alkemy.dto.MovieDto;
import com.challenge.alkemy.entity.Character;
import com.challenge.alkemy.entity.Movie;
import com.challenge.alkemy.exception.SpringException;
import com.challenge.alkemy.repository.CharacterRepository;
import com.challenge.alkemy.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Service
public class CharacterServiceImpl implements CharacterService{

    @Autowired
    private CharacterRepository characterRepository;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private CharacterConverter characterConverter;

    private final String ERROR_1 = "Error in server";

    @Override
    public String generateId() {
        String id;
        do{
            id = UUID.randomUUID().toString().replaceAll("[^0-9]", "").substring(0, 15);
        }while(characterRepository.existsById(id));
        return id;
    }

    @Override
    public CharacterDto create(CharacterDto characterDto) throws SpringException {
        try{
            String id = generateId();
            characterDto.setId(id);

            Character character = characterConverter.convertToEntity(characterDto);
            characterRepository.save(character);

            return characterConverter.convertToDto(character);

        }catch (SpringException e){
            throw e;
        }catch (Exception e){
            throw new SpringException(ERROR_1);
        }
    }

    @Override
    public CharacterDto update(CharacterDto characterDto) throws SpringException {
        try{
            Character character = characterRepository.findByIdAndDateOffIsNull(characterDto.getId());

            if(character == null){
                throw new SpringException("Character not found", HttpStatus.NOT_FOUND);
            }
            for(MovieDto movieDto : characterDto.getMovies()){
                Movie movie = movieRepository.findByTitleAndDateOffIsNull(movieDto.getTitle());

                if(movie == null){
                    throw new SpringException("Movie not found", HttpStatus.NOT_FOUND);
                }
                character.getMovies().add(movie);
            }
            characterRepository.save(character);
            return characterConverter.convertToDto(character);

        }catch (SpringException e){
            throw e;
        }catch (Exception e){
            throw new SpringException(ERROR_1);
        }
    }

    @Override
    public Set<CharacterDto> getCharacters() throws SpringException {
        try{
            Set<CharacterDto> characterDtos = new HashSet<>();

            for(Character character : characterRepository.findByDateOffIsNull()){
                characterDtos.add(characterConverter.convertToDto(character));
            }
            if(characterDtos.isEmpty()){
                throw new SpringException("There are no characters in the database");
            }
            return characterDtos;

        }catch (SpringException e){
            throw e;
        }catch (Exception e){
            throw new SpringException(ERROR_1);
        }
    }

    @Override
    public Set<CharacterDto> getCharactersResumeDto() throws SpringException {
        try{
            Set<Character> characters = characterRepository.findByDateOffIsNull();
            Set<CharacterDto> characterResumeDtos = new HashSet<>();

            if(characters.isEmpty()){
                throw new SpringException("There are no characters in the database");
            }

            for(Character character : characters){
                CharacterDto characterDto = new CharacterDto();
                characterDto.setName(character.getName());
                characterDto.setImage(character.getImage());

                characterResumeDtos.add(characterDto);
            }
            return characterResumeDtos;

        }catch (SpringException e){
            throw e;
        }catch (Exception e){
            throw new SpringException(ERROR_1);
        }
    }

    @Override
    public CharacterDto getCharacter(String id) throws SpringException {
        try{
            Character character = characterRepository.findByIdAndDateOffIsNull(id);

            if(character == null){
                throw new SpringException("Character not found", HttpStatus.NOT_FOUND);
            }
            return characterConverter.convertToDto(character);

        }catch (SpringException e){
            throw e;
        }catch (Exception e){
            throw new SpringException(ERROR_1);
        }
    }

    @Override
    public Set<CharacterDto> getCharacterByName(String name) throws SpringException {
        try{
            Set<Character> characters = characterRepository.findByNameAndDateOffIsNull(name);
            Set<CharacterDto> characterDtos = new HashSet<>();

            if(characters.isEmpty()){
                throw new SpringException("Character not found", HttpStatus.NOT_FOUND);
            }
            for(Character character : characters){
                characterDtos.add(characterConverter.convertToDto(character));
            }
            return characterDtos;

        }catch (SpringException e){
            throw e;
        }catch (Exception e){
            throw new SpringException(ERROR_1);
        }
    }

    @Override
    public Set<CharacterDto> getCharactersByAge(Integer age) throws SpringException {
        try{
            Set<Character> characters = characterRepository.findByAgeAndDateOffIsNull(age);
            Set<CharacterDto> characterDtos = new HashSet<>();

            if(characters.isEmpty()){
                throw new SpringException("Character not found", HttpStatus.NOT_FOUND);
            }
            for(Character character : characters){
                characterDtos.add(characterConverter.convertToDto(character));
            }
            return characterDtos;

        }catch (SpringException e){
            throw e;
        }catch (Exception e){
            throw new SpringException(ERROR_1);
        }
    }

    @Override
    public Set<CharacterDto> getCharactersByMovie(String id) throws SpringException {
        try{
            Movie movie = movieRepository.findByIdAndDateOffIsNull(id);
            Set<CharacterDto> characterDtos = new HashSet<>();

            if(movie == null){
                throw new SpringException("Movie not found");
            }
            for(Character character : movie.getCharacters()){
                characterDtos.add(characterConverter.convertToDto(character));
            }
            return characterDtos;

        }catch (SpringException e){
            throw e;
        }catch (Exception e){
            throw new SpringException(ERROR_1);
        }
    }

    @Override
    public void delete(String id) throws SpringException {
        try{
            Character character = characterRepository.findByIdAndDateOffIsNull(id);

            if(character == null){
                throw new SpringException("Character not found");
            }
            character.setDateOff(new Date());
            characterRepository.save(character);

        }catch (SpringException e){
            throw e;
        }catch (Exception e){
            throw new SpringException(ERROR_1);
        }
    }
}
