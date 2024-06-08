package org.jamielb.utils;

import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

public class RestUtils {

    private static final String CORS = "Access-Control-Allow-Origin";
    private static final String ALL_ORIGINS = "*";

    private RestUtils() {
    }

    public static Response.ResponseBuilder addCommonHeaders(Response.ResponseBuilder builder) {
        return builder.header(CORS, ALL_ORIGINS);
    }

    public static Response errorResponse(Exception e) {
        Response.ResponseBuilder builder = Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(e.getMessage())
                .type(MediaType.TEXT_PLAIN);
        return addCommonHeaders(builder).build();
    }

}
