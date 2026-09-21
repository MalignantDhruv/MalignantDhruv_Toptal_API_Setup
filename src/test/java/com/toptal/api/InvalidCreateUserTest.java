package com.toptal.api;

import org.testng.annotations.Test;

import com.toptal.api.base.BaseTest;
import com.toptal.api.constants.Endpoints;

import static io.restassured.RestAssured.*;

public class InvalidCreateUserTest extends BaseTest {

    @Test
    public void createUserWithoutNameTest() {

        String requestBody = """
                {
                    "job": "QA Engineer"
                }
                """;

        given(requestSpec)
            .body(requestBody)

        .when()
            .post(Endpoints.CREATE_USER)

        .then()
            .statusCode(201);
    }
}