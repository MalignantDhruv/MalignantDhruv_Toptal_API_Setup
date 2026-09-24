package com.toptal.api;

import org.testng.annotations.Test;

import com.toptal.api.base.BaseTest;
import com.toptal.api.constants.Endpoints;

import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static org.testng.Assert.*;

public class ResponseExtractionTest extends BaseTest {

    @Test
    public void extractUserIdTest() {

        Response response =
                given(requestSpec)
                .pathParam("id", "1")

                .when()
                    .get(Endpoints.USER_BY_ID)

                .then()
                    .statusCode(200)
                    .extract()
                    .response();

        String userId = response.jsonPath().getString("id");

        assertEquals(userId, "1");
    }
}