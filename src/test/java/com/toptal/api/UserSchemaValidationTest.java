package com.toptal.api;

import org.testng.annotations.Test;

import com.toptal.api.base.BaseTest;
import com.toptal.api.constants.Endpoints;

import static io.restassured.RestAssured.*;
import static io.restassured.module.jsv.JsonSchemaValidator.*;

public class UserSchemaValidationTest extends BaseTest {

    @Test
    public void validateUserResponseSchemaTest() {

        given(requestSpec)
            .pathParam("id", 7)

        .when()
            .get(Endpoints.USER_BY_ID)

        .then()
            .statusCode(200)
            .body(matchesJsonSchemaInClasspath(
                    "schemas/user-schema.json"
            ));
    }
}