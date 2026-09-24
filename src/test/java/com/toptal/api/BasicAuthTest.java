package com.toptal.api;

import org.testng.annotations.Test;

import com.toptal.api.base.HttpBinBaseTest;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class BasicAuthTest extends HttpBinBaseTest {

    @Test
    public void validBasicAuthTest() {

        given(requestSpec)
            .auth()
            .preemptive()
            .basic("testuser", "testpass")

        .when()
            .get("/basic-auth/testuser/testpass")

        .then()
            .statusCode(200)
            .body("authenticated", equalTo(true))
            .body("user", equalTo("testuser"));
    }
}