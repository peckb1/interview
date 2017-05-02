package com.github.peckb1.interview.model;

import com.github.peckb1.processor.AutoJackson;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@AutoJackson
public interface Album {

    @NotBlank
    String getArtistName();

    @NotBlank
    String getAlbumName();

    @NotNull
    @Min(message = "No records were released before 1800", value = 1800)
    Integer getReleaseYear();

}
