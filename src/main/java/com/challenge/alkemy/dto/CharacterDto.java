package com.challenge.alkemy.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Set;

@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CharacterDto implements Serializable {

    private static final long serialVersionUID = 1234L;

   
    private String id;
    @NotBlank
    private String name;
    @NotNull
    @Min(value = 0)
    private Integer age;
    @NotNull
    @Min(value = 0)
    private Double weight;
    @NotBlank
    private String history;
    @NotBlank
    private String image;
    @NotNull
    @Min(value = 0)
    private Set<MovieDto> movies;
}
