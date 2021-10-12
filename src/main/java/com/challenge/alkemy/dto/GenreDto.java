package com.challenge.alkemy.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GenreDto implements Serializable {

    private static final long serialVersionUID = 1234L;


    private String id;
    @NotBlank
    private String name;
    @NotBlank
    private String image;
    private Date dateOff;
    @NotNull
    @Min(value = 0)
    private Set<MovieDto> movies;
}
