package com.challenge.alkemy.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MovieDto implements Serializable {

    private static final long serialVersionUID = 1234L;

    private String id;
    @NotBlank
    private String title;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date creationDate;
    @NotNull
    @Min(value = 0)
    private Integer rating;
    @NotBlank
    private String image;
    private List<CharacterDto> characters;
    @NotBlank
    private String genreId;

}
