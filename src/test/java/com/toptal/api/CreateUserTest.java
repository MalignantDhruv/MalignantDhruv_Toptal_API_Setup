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
                    "name": "Arjun Mehta",
                    "email": "arjun.mehta@example.com",
                    "gender": "Male",
                    "status": "Active"
                }
                """;

        given(requestSpec)
            .body(requestBody)

        .when()
            .post(Endpoints.CREATE_USER)

        .then()
            .statusCode(201)
            .body("name", equalTo("Arjun Mehta"))
            .body("email", equalTo("arjun.mehta@example.com"))
            .body("gender", equalTo("Male"))
            .body("status", equalTo("Active"))
            .body("id", notNullValue());
    }
}