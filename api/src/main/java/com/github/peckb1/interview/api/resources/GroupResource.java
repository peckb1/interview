package com.github.peckb1.interview.api.resources;

import com.codahale.metrics.annotation.Timed;
import com.github.peckb1.interview.api.resources.response.Responses;
import com.github.peckb1.interview.model.Group;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.MediaType;

@Path("/group")
public class GroupResource {

    @POST
    @Timed
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public void createArtist(@NotNull Group group, @Suspended AsyncResponse asyncResponse) {

        // TODO
        asyncResponse.resume(Responses.notImplemented());

    }

    @GET
    @Timed
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{group_name}")
    public void getArtist(@NotEmpty @PathParam("group_name") String groupName, @Suspended AsyncResponse asyncResponse) {

        // TODO
        asyncResponse.resume(Responses.notImplemented());

    }

}
