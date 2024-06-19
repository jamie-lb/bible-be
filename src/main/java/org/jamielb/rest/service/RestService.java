package org.jamielb.rest.service;

import java.util.List;

import org.jamielb.model.businessobjects.BibleVersion;
import org.jamielb.model.businessobjects.Book;
import org.jamielb.model.businessobjects.Verse;
import org.jamielb.service.BibleService;
import org.jamielb.utils.RestUtils;
import org.jboss.logging.Logger;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
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
    @Path("versions")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getVersions() {
        try {
            List<BibleVersion> versions = bibleService.getAllVersions();
            return RestUtils.addCommonHeaders(Response.ok(versions, MediaType.APPLICATION_JSON)).build();
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
            List<Book> books = bibleService.getAllBooks();
            return RestUtils.addCommonHeaders(Response.ok(books, MediaType.APPLICATION_JSON)).build();
        } catch (Exception e) {
            LOGGER.error(e, e.fillInStackTrace());
            return RestUtils.errorResponse(e);
        }
    }

    @GET
    @Path("allVerses/{versionCode}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllVersesInVersion(@PathParam("versionCode") String versionCode) {
        try {
            List<Verse> verses = bibleService.getVersionVerses(versionCode);
            return RestUtils.addCommonHeaders(Response.ok(verses, MediaType.APPLICATION_JSON)).build();
        } catch (Exception e) {
            LOGGER.error(e, e.fillInStackTrace());
            return RestUtils.errorResponse(e);
        }
    }

    @GET
    @Path("verse/{versionCode}/{bookId}/{chapterNumber}/{verseNumber}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getVerse(
            @PathParam("versionCode") String versionCode,
            @PathParam("bookId") int bookId,
            @PathParam("chapterNumber") int chapterNumber, @PathParam("verseNumber") int verseNumber) {
        try {
            Verse verse = bibleService.getVerse(versionCode, bookId, chapterNumber, verseNumber);
            return RestUtils.addCommonHeaders(Response.ok(verse, MediaType.APPLICATION_JSON)).build();
        } catch (Exception e) {
            LOGGER.error(e, e.fillInStackTrace());
            return RestUtils.errorResponse(e);
        }
    }

    @GET
    @Path("{versionCode}/chapters/{bookId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getChapterListForBook(
            @PathParam("versionCode") String versionCode, @PathParam("bookId") int bookId) {
        try {
            List<Integer> chapterNumbers = bibleService.getBookChapters(versionCode, bookId);
            return RestUtils.addCommonHeaders(Response.ok(chapterNumbers, MediaType.APPLICATION_JSON)).build();
        } catch (Exception e) {
            LOGGER.error(e, e.fillInStackTrace());
            return RestUtils.errorResponse(e);
        }
    }

    @GET
    @Path("{versionCode}/verses/{bookId}/{chapterNumber}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getVerseNumbersInBookChapter(
            @PathParam("versionCode") String versionCode,
            @PathParam("bookId") int bookId, @PathParam("chapterNumber") int chapterNumber) {
        try {
            List<Integer> verseNumbers = bibleService.getChapterVerses(versionCode, bookId, chapterNumber);
            return RestUtils.addCommonHeaders(Response.ok(verseNumbers, MediaType.APPLICATION_JSON)).build();
        } catch (Exception e) {
            LOGGER.error(e, e.fillInStackTrace());
            return RestUtils.errorResponse(e);
        }
    }

}
