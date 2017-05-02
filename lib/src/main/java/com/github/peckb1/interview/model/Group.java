package com.github.peckb1.interview.model;

import com.github.peckb1.processor.AutoJackson;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

@AutoJackson
public interface Group {

    @NotBlank
    String getArtistName();

    @NotNull
    Integer getYearFormed();

    Optional<Integer> getYearDisbanded();

    List<String> currentMembers();

    List<String> pasMembers();

}
