package com.toptal.api;

import org.testng.annotations.Test;

import com.toptal.api.base.BaseTest;
import com.toptal.api.constants.Endpoints;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class CreateUserTest extends BaseTest {

    @Test
    public void createUserTest() {

        String requestBody = """
                {
                    "name": "Dhruv",
                    "job": "QA Engineer"
                }
                """;

        given(requestSpec)
            .body(requestBody)

        .when()
            .post(Endpoints.CREATE_USER)

        .then()
            .statusCode(201)
            .body("name", equalTo("Dhruv"))
            .body("job", equalTo("QA Engineer"));
    }
}