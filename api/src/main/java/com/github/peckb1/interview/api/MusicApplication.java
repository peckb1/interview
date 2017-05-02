package com.github.peckb1.interview.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.github.peckb1.interview.api.health.MySqlHealthCheck;
import com.github.peckb1.interview.api.resources.AlbumResource;
import com.github.peckb1.interview.api.resources.GroupResource;
import com.github.peckb1.interview.api.resources.MusicResource;
import io.dropwizard.Application;
import io.dropwizard.configuration.ConfigurationSourceProvider;
import io.dropwizard.configuration.EnvironmentVariableSubstitutor;
import io.dropwizard.configuration.SubstitutingSourceProvider;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import peckb1.autojackson.AutoJacksonSetup;

public class MusicApplication extends Application<MusicConfiguration> {

    public static void main(String[] args) throws Exception {
        new MusicApplication().run(args);
    }

    @Override
    public String getName() {
        return "Interview-API";
    }

    @Override
    public void initialize(Bootstrap<MusicConfiguration> bootstrap) {
        // Enable variable substitution with environment variables
        final boolean strictSubstitution = true; // require environment variables to be present
        final ConfigurationSourceProvider sourceProvider = bootstrap.getConfigurationSourceProvider();
        final EnvironmentVariableSubstitutor substitutor = new EnvironmentVariableSubstitutor(strictSubstitution);
        bootstrap.setConfigurationSourceProvider(new SubstitutingSourceProvider(sourceProvider, substitutor));
    }

    @Override
    public void run(MusicConfiguration musicConfiguration, Environment environment) throws Exception {
        // setup our deserialization ObjectMapper
        ObjectMapper objectMapper = environment.getObjectMapper();
        objectMapper.registerModule(new Jdk8Module());
        AutoJacksonSetup.configureObjectMapper(objectMapper);

        // TODO use this data when talking to the database
        String mySqlHost = musicConfiguration.getMySqlHost();
        int mySqlPort = musicConfiguration.getMySqlPort();
        String databaseName = musicConfiguration.getDatabaseName();
        String databaseUsername = musicConfiguration.getDatabaseUsername();
        String databasePassword = musicConfiguration.getDatabasePassword();

        // Setup the resources
        final MusicResource musicResource = new MusicResource();
        final AlbumResource albumResource = new AlbumResource();
        final GroupResource groupResource = new GroupResource();
        environment.jersey().register(musicResource);
        environment.jersey().register(albumResource);
        environment.jersey().register(groupResource);

        // setup the health checks
        MySqlHealthCheck mySqlHealthCheck = new MySqlHealthCheck();
        environment.healthChecks().register("mySql", mySqlHealthCheck);
    }
}
