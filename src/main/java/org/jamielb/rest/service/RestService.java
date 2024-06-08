package org.jamielb.rest.service;

import java.util.List;

import org.jamielb.service.BibleService;
import org.jamielb.utils.RestUtils;
import org.jboss.logging.Logger;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("")
public class RestService {

    private static final Logger LOGGER = Logger.getLogger(RestService.class);

    @Inject
    BibleService bibleService;

    @GET
    @Path("healthcheck")
    @Produces(MediaType.TEXT_PLAIN)
    public Response healthcheck() {
        try {
            return RestUtils.addCommonHeaders(Response.ok("Service is up and running", MediaType.TEXT_PLAIN)).build();
        } catch (Exception e) {
            LOGGER.error(e, e.fillInStackTrace());
            return RestUtils.errorResponse(e);
        }
    }

    @GET
    @Path("books")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBooks() {
        try {
            List<String> books = bibleService.getBooks();
            return RestUtils.addCommonHeaders(Response.ok(books, MediaType.APPLICATION_JSON)).build();
        } catch (Exception e) {
            LOGGER.error(e, e.fillInStackTrace());
            return RestUtils.errorResponse(e);
        }
    }

}
