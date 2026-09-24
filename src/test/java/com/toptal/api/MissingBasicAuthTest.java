package com.toptal.api;

import org.testng.annotations.Test;

import com.toptal.api.base.HttpBinBaseTest;

import static io.restassured.RestAssured.*;

public class MissingBasicAuthTest extends HttpBinBaseTest {

    @Test
    public void missingBasicAuthTest() {

        given(requestSpec)

        .when()
            .get("/basic-auth/testuser/testpass")

        .then()
            .statusCode(401);
    }
}