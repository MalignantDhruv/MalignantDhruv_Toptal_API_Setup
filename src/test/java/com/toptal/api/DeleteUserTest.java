package com.toptal.api;

import org.testng.annotations.Test;

import com.toptal.api.base.BaseTest;
import com.toptal.api.constants.Endpoints;

import static io.restassured.RestAssured.*;

public class DeleteUserTest extends BaseTest {

    @Test
    public void deleteUserTest() {

        given(requestSpec)
            .pathParam("id", 1)

        .when()
            .delete(Endpoints.DELETE_USER)

        .then()
            .statusCode(200);
    }
}