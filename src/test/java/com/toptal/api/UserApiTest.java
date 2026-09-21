package com.toptal.api;

import org.testng.annotations.Test;

import com.toptal.api.base.BaseTest;
import com.toptal.api.constants.Endpoints;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class UserApiTest extends BaseTest {

    @Test
    public void getUserTest() {

        given(requestSpec)
            .pathParam("id", 1)

        .when()
            .get(Endpoints.USER_BY_ID)

        .then()
            .statusCode(200)
            .body("id", equalTo(1));
    }
}