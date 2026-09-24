package com.toptal.api;

import org.testng.annotations.Test;

import com.toptal.api.base.BaseTest;
import com.toptal.api.constants.Endpoints;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class UpdateUserTest extends BaseTest {

    @Test
    public void updateUserTest() {

        String requestBody = """
                {
                    "name": "Dhruv Mehta Updated",
                    "email": "dhruv.updated@example.com",
                    "gender": "Male",
                    "status": "Active"
                }
                """;

        given(requestSpec)
            .pathParam("id", "1")
            .body(requestBody)

        .when()
            .put(Endpoints.UPDATE_USER)

        .then()
            .statusCode(200)
            .body("id", equalTo("1"))
            .body("name", equalTo("Dhruv Mehta Updated"))
            .body("email", equalTo("dhruv.updated@example.com"))
            .body("gender", equalTo("Male"))
            .body("status", equalTo("Active"));
    }
}