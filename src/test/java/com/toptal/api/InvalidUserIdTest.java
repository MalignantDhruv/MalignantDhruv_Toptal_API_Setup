package com.toptal.api;

import org.testng.annotations.Test;

import com.toptal.api.base.BaseTest;
import com.toptal.api.constants.Endpoints;

import static io.restassured.RestAssured.*;

public class InvalidUserIdTest extends BaseTest {

    @Test
    public void getUserWithInvalidIdFormatTest() {

        given(requestSpec)
            .pathParam("id", "abc")

        .when()
            .get(Endpoints.USER_BY_ID)

        .then()
            .statusCode(404);
    }
}