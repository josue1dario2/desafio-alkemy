package com.challenge.alkemy.controller;

import com.challenge.alkemy.dto.CharacterDto;
import com.challenge.alkemy.exception.SpringException;
import com.challenge.alkemy.repository.CharacterRepository;
import com.challenge.alkemy.service.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/characters")
public class CharacterController {

    @Autowired
    CharacterRepository characterRepository;

    @Autowired
    CharacterService characterService;

    @GetMapping
    public ResponseEntity<?> getCharacters(){
        try{

            Set<CharacterDto> characterDtos = characterService.getCharacters();
            return new ResponseEntity<>(characterDtos, OK);

        }catch (SpringException e){

            Map<String,Object> response = new HashMap<>();
            response.put("error",e.getMessage());
            return new ResponseEntity<>(response,INTERNAL_SERVER_ERROR);

        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getCharacter(@PathVariable String id){
        try{

            CharacterDto characterDto = characterService.getCharacter(id);
            return new ResponseEntity<>(characterDto,OK);

        }catch (SpringException e){

            Map<String,Object> response = new HashMap<>();
            response.put("error",e.getMessage());
            return new ResponseEntity<>(response,INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody CharacterDto characterDto, BindingResult result){

        Map<String,Object> response = new HashMap<>();

        if(result.hasErrors()){

            List<String> errors = new ArrayList<>();
            for(FieldError fieldError : result.getFieldErrors()){
                errors.add(fieldError.getDefaultMessage());
            }
            response.put("errros",errors);
            return new ResponseEntity<>(response,BAD_REQUEST);
        }
        try{

            characterService.create(characterDto);
            return new ResponseEntity<>(characterDto,CREATED);

        }catch (SpringException e){
            response.put("error",e.getMessage());
            return new ResponseEntity<>(response,e.getStatus());
        }
    }
    @PutMapping
    public ResponseEntity<?> update(@Valid @RequestBody CharacterDto characterDto,BindingResult result){

        Map<String,Object> response = new HashMap<>();

        if(result.hasErrors()){

            List<String> errors = new ArrayList<>();
            for(FieldError fieldError : result.getFieldErrors()){
                errors.add(fieldError.getDefaultMessage());
            }
            response.put("errors",errors);
            return new ResponseEntity<>(response,BAD_REQUEST);
        }
        try{
            characterService.update(characterDto);
            return new ResponseEntity<>(characterDto,CREATED);

        }catch (SpringException e){
            response.put("error",e.getMessage());
            return new ResponseEntity<>(response,e.getStatus());
        }

    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id){
        try{

            characterService.delete(id);
            return new ResponseEntity<>(OK);

        }catch (SpringException e){

            Map<String,Object> response = new HashMap<>();
            response.put("error",e.getMessage());
            return new ResponseEntity<>(response,e.getStatus());
        }
    }
    @GetMapping("/resume")
    public ResponseEntity<?> getCharactersResume(){
        try{

            Set<CharacterDto> characterDtos = characterService.getCharactersResumeDto();
            return new ResponseEntity<>(characterDtos,OK);

        }catch (SpringException e){

            Map<String,Object> response = new HashMap<>();
            response.put("error",e.getMessage());
            return new ResponseEntity<>(response,INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/{name}")
    public ResponseEntity<?> getCharacterByName(@PathVariable String name){
        try{

            Set<CharacterDto> characterDtos = characterService.getCharacterByName(name);
            return new ResponseEntity<>(characterDtos,OK);

        }catch (SpringException e){

            Map<String,Object> response = new HashMap<>();
            response.put("error",e.getMessage());
            return new ResponseEntity<>(response,INTERNAL_SERVER_ERROR);

        }
    }
    @GetMapping("/age/{age}")//agregamos por age
    public ResponseEntity<?> getCharacterByAge(@PathVariable Integer age){
        try{

            Set<CharacterDto> characterDtos = characterService.getCharactersByAge(age);
            return new ResponseEntity<>(characterDtos,OK);

        }catch (SpringException e){

            Map<String,Object> response = new HashMap<>();
            response.put("error",e.getMessage());
            return new ResponseEntity<>(response,INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/movie/{id}")
    public ResponseEntity<?> getCharacterByMovie(@PathVariable String id){
        try{

            Set<CharacterDto> characterDtos = characterService.getCharactersByMovie(id);
            return new ResponseEntity<>(characterDtos,OK);

        }catch (SpringException e){

            Map<String,Object> response = new HashMap<>();
            response.put("error",e.getMessage());
            return new ResponseEntity<>(response,INTERNAL_SERVER_ERROR);
        }
    }
}
