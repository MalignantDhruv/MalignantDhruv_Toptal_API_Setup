package com.toptal.api;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class UserApiTest {

    @Test
    public void getUserTest() {

        given()
            .baseUri("https://jsonplaceholder.typicode.com")

        .when()
            .get("/users/1")

        .then()
            .statusCode(200)
            .body("id", equalTo(1));
    }
}