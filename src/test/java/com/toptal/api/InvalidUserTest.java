package com.toptal.api;

import org.testng.annotations.Test;

import com.toptal.api.base.BaseTest;
import com.toptal.api.constants.Endpoints;

import static io.restassured.RestAssured.*;

public class InvalidUserTest extends BaseTest {

    @Test
    public void getInvalidUserTest() {

        given(requestSpec)
            .pathParam("id", 9999)

        .when()
            .get(Endpoints.USER_BY_ID)

        .then()
            .statusCode(404);
    }
}