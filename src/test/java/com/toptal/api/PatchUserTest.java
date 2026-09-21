package com.toptal.api;

import org.testng.annotations.Test;

import com.toptal.api.base.BaseTest;
import com.toptal.api.constants.Endpoints;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class PatchUserTest extends BaseTest {

    @Test
    public void patchUserTest() {

        String requestBody = """
                {
                    "job": "QA Automation Engineer"
                }
                """;

        given(requestSpec)
            .pathParam("id", 1)
            .body(requestBody)

        .when()
            .patch(Endpoints.PATCH_USER)

        .then()
            .statusCode(200)
            .body("job", equalTo("QA Automation Engineer"));
    }
}