package com.toptal.api;

import org.testng.annotations.Test;

import com.toptal.api.base.BaseTest;
import com.toptal.api.constants.Endpoints;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class JsonArrayTest extends BaseTest {

    @Test
    public void validateUserArrayTest() {

        given(requestSpec)

        .when()
            .get(Endpoints.USERS)

        .then()
            .statusCode(200)
            .body("$", not(empty()))
            .body("name", everyItem(notNullValue()))
            .body("email", everyItem(notNullValue()))
            .body("gender", everyItem(notNullValue()))
            .body("status", everyItem(notNullValue()));
    }
}