package com.toptal.api;

import org.testng.annotations.Test;

import com.toptal.api.base.HttpBinBaseTest;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class HttpBinTest extends HttpBinBaseTest {

    @Test
    public void getRequestTest() {

        given(requestSpec)

        .when()
            .get("/get")

        .then()
            .statusCode(200)
            .body("url", containsString("/get"));
    }
}