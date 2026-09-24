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
            .body("size()", greaterThan(0))
            .body("[0].id", notNullValue())
            .body("[0].name", notNullValue())
            .body("[0].email", notNullValue())
            .body("[0].gender", notNullValue())
            .body("[0].status", notNullValue());
    }
}