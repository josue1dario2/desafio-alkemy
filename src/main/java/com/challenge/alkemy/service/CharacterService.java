package com.challenge.alkemy.service;

import com.challenge.alkemy.dto.CharacterDto;
import com.challenge.alkemy.exception.SpringException;

import java.util.Set;

public interface CharacterService {

    public String generateId();

    public CharacterDto create(CharacterDto characterDto) throws SpringException;

    public CharacterDto update(CharacterDto characterDto) throws SpringException;

    public Set<CharacterDto> getCharacters() throws SpringException;

    public Set<CharacterDto> getCharactersResumeDto() throws SpringException;

    public CharacterDto getCharacter(String id) throws SpringException;

    public Set<CharacterDto> getCharacterByName(String name) throws SpringException;

    public Set<CharacterDto> getCharactersByAge(Integer age) throws SpringException;

    public Set<CharacterDto> getCharactersByMovie(String id) throws SpringException;

    public void delete(String id) throws SpringException;


}
