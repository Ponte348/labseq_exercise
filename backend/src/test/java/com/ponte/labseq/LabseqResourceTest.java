package com.ponte.labseq;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
class LabseqResourceTest {
    @Test
    void testHelloEndpoint() {
        given()
          .when().get("/labseq")
          .then()
             .statusCode(200)
             .body(is("Welcome to the labseq exercise."));
    }

}