package org.jamielb;

import io.quarkus.test.junit.QuarkusTest;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
class RestServiceTest {

    @Test
    void testHealthcheckEndpoint() {
        given().when().get("/healthcheck").then().statusCode(200).body(is("Service is up and running"));
    }

    @Test
    void testGetAllVersesEndpoint() {
        given().when().get("/allVerses/ESV").then().statusCode(200);
    }

}
