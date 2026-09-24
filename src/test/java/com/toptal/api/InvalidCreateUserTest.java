package com.toptal.api;

import org.testng.annotations.Test;

import com.toptal.api.base.BaseTest;
import com.toptal.api.constants.Endpoints;

import static io.restassured.RestAssured.*;

public class InvalidCreateUserTest extends BaseTest {

    @Test
    public void createUserWithInvalidJsonTest() {

        String invalidRequestBody = """
                {
                    "name": "Invalid User",
                    "email": "invalid.user@example.com",
                    "gender": "Male",
                    "status": "Active"
                """;

        given(requestSpec)
            .body(invalidRequestBody)

        .when()
            .post(Endpoints.CREATE_USER)

        .then()
            .statusCode(400);
    }
}