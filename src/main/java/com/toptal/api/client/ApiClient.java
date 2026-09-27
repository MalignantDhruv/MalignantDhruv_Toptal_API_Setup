package com.toptal.api.client;


import com.toptal.api.constants.Endpoints;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.*;

public class ApiClient {

    private RequestSpecification requestSpec;

    public ApiClient(RequestSpecification requestSpec) {
        this.requestSpec = requestSpec;
    }

    public Response getAllUsers() {

        return given(requestSpec)
        .when()
            .get(Endpoints.USERS);
    }

    public Response getUserById(String userId) {

        return given(requestSpec)
            .pathParam("id", userId)
        .when()
            .get(Endpoints.USER_BY_ID);
    }

    public Response createUser(String requestBody) {

        return given(requestSpec)
            .body(requestBody)
        .when()
            .post(Endpoints.CREATE_USER);
    }

    public Response updateUser(String userId, String requestBody) {

        return given(requestSpec)
            .pathParam("id", userId)
            .body(requestBody)
        .when()
            .put(Endpoints.UPDATE_USER);
    }

    public Response patchUser(String userId, String requestBody) {

        return given(requestSpec)
            .pathParam("id", userId)
            .body(requestBody)
        .when()
            .patch(Endpoints.PATCH_USER);
    }

    public Response deleteUser(String userId) {

        return given(requestSpec)
            .pathParam("id", userId)
        .when()
            .delete(Endpoints.DELETE_USER);
    }
}