package com.toptal.api;

import org.testng.annotations.Test;

import com.toptal.api.base.HttpBinBaseTest;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class JsonResponseTest extends HttpBinBaseTest {

    @Test
    public void validateJsonResponseTest() {

        given(requestSpec)
            .queryParam("name", "Dhruv")
            .queryParam("role", "QA")

        .when()
            .get("/get")

        .then()
            .statusCode(200)
            .body("args.name", equalTo("Dhruv"))
            .body("args.role", equalTo("QA"))
            .body("url", containsString("/get"));
    }
}