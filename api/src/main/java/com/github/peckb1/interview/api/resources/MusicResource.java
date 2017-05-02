package com.github.peckb1.interview.api.resources;

import com.codahale.metrics.annotation.Timed;
import com.github.peckb1.interview.api.resources.response.Responses;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.MediaType;
import java.util.Optional;

@Path("/music")
public class MusicResource {

    @GET
    @Timed
    @Produces(MediaType.APPLICATION_JSON)
    public void getCollection(@QueryParam("group") Optional<String> groupNameOptional, @Suspended AsyncResponse asyncResponse) {

        // TODO
        asyncResponse.resume(Responses.notImplemented());

    }



}
