package com.toptal.api;

import org.testng.annotations.Test;

import com.toptal.api.base.HttpBinBaseTest;

import static io.restassured.RestAssured.*;

public class InvalidBasicAuthTest extends HttpBinBaseTest {

    @Test
    public void invalidBasicAuthTest() {

        given(requestSpec)
            .auth()
            .preemptive()
            .basic("testuser", "wrongpassword")

        .when()
            .get("/basic-auth/testuser/testpass")

        .then()
            .statusCode(401);
    }
}