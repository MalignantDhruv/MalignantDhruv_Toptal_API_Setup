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
                    "name": "Dhruv Updated",
                    "job": "Senior QA Engineer"
                }
                """;

        given(requestSpec)
            .pathParam("id", 1)
            .body(requestBody)

        .when()
            .put(Endpoints.UPDATE_USER)

        .then()
            .statusCode(200)
            .body("name", equalTo("Dhruv Updated"))
            .body("job", equalTo("Senior QA Engineer"));
    }
}