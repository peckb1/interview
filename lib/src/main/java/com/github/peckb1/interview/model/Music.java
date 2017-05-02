package com.github.peckb1.interview.model;

import com.github.peckb1.processor.AutoJackson;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

@AutoJackson
public interface Music {

    @NotNull
    Map<Group, List<Album>> getMusic();

}
