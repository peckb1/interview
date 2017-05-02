package com.github.peckb1.interview.api.resources.response;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

public final class Responses {
    private Responses() {
    }

    public static Response conflict() {
        return Response.status(Response.Status.CONFLICT).build();
    }

    public static Response created() {
        return Response.status(Response.Status.CREATED).build();
    }

    public static Response notFound() {
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @Deprecated
    public static Response notImplemented() {
        return Response.status(Status.NOT_IMPLEMENTED).build();
    }

    public static Response internalServerError() {
        return Response.serverError().build();
    }

    public static Response ok(Object entity, String mediaType) {
        return Response.ok().entity(entity).type(mediaType).build();
    }
}
