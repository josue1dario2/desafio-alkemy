package com.challenge.alkemy.controller;

import com.challenge.alkemy.dto.MovieDto;
import com.challenge.alkemy.exception.SpringException;
import com.challenge.alkemy.service.MovieService;
import com.challenge.alkemy.service.MovieServiceImpl;
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
@RequestMapping("/movies")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @GetMapping
    public ResponseEntity<?> getMovies(){
        try{
            Set<MovieDto> movieDtos = movieService.getMovies();
            return new ResponseEntity<>(movieDtos,OK);

        }catch (SecurityException | SpringException e){

            Map<String,Object> response = new HashMap<>();
            response.put("error",e.getMessage());
            return new ResponseEntity<>(response, INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody MovieDto movieDto, BindingResult result){

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
            movieService.create(movieDto);
            return new ResponseEntity<>(movieDto,CREATED);

        } catch (SpringException e) {
            response.put("error",e.getMessage());
            return new ResponseEntity<>(response,e.getStatus());
        }
    }
}
