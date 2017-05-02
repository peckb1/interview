package com.github.peckb1.interview.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

public class MusicConfiguration extends Configuration {

    @NotEmpty
    private final String mySqlHost;

    @NotEmpty
    private final String databaseName;

    @NotEmpty
    private final String databaseUsername;

    @NotEmpty
    private final String databasePassword;

    @NotNull
    private final Integer mySqlPort;

    public MusicConfiguration(@JsonProperty(value = "mySqlHost", required = true) String mySqlHost,
                              @JsonProperty(value = "mySqlPort", required = true) Integer mySqlPort,
                              @JsonProperty(value = "databaseName", required = true) String databaseName,
                              @JsonProperty(value = "databaseUsername", required = true) String databaseUsername,
                              @JsonProperty(value = "databasePassword", required = true) String databasePassword) {
        this.mySqlHost = mySqlHost;
        this.mySqlPort = mySqlPort;
        this.databaseName = databaseName;
        this.databaseUsername = databaseUsername;
        this.databasePassword = databasePassword;
    }

    String getMySqlHost() {
        return mySqlHost;
    }

    int getMySqlPort() {
        return mySqlPort;
    }

    String getDatabaseName() {
        return databaseName;
    }

    String getDatabaseUsername() {
        return databaseUsername;
    }

    String getDatabasePassword() {
        return databasePassword;
    }
}
