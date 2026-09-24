package com.toptal.api;

import org.testng.annotations.Test;

import com.toptal.api.base.HttpBinBaseTest;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class ResponseHeaderTest extends HttpBinBaseTest {

    @Test
    public void responseHeaderTest() {

        given(requestSpec)

        .when()
            .get("/get")

        .then()
            .statusCode(200)
            .contentType("application/json")
            .header("Content-Type", containsString("application/json"));
    }
}