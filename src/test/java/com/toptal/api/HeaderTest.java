package com.toptal.api;

import org.testng.annotations.Test;

import com.toptal.api.base.HttpBinBaseTest;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class HeaderTest extends HttpBinBaseTest {

    @Test
    public void sendCustomHeaderTest() {

        given(requestSpec)
            .header("X-Test-Client", "ToptalQA")

        .when()
            .get("/headers")

        .then()
            .statusCode(200)
            .body("headers.X-Test-Client", equalTo("ToptalQA"));
    }
}