package com.toptal.api;

import org.testng.annotations.Test;

import com.toptal.api.base.HttpBinBaseTest;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class HeaderContentTypeTest extends HttpBinBaseTest {

    @Test
    public void contentTypeHeaderTest() {

        given(requestSpec)
            .header("Accept", "application/json")

        .when()
            .get("/get")

        .then()
            .statusCode(200)
            .body("headers.Accept", equalTo("application/json"));
    }
}