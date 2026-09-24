package com.toptal.api;

import org.testng.annotations.Test;

import com.toptal.api.base.BaseTest;
import com.toptal.api.config.ConfigReader;
import com.toptal.api.constants.Endpoints;

import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.testng.Assert.*;

public class ApiChainingTest extends BaseTest {

    @Test
    public void completeUserWorkflowTest() {

        // =========================================================
        // STEP 1: CREATE USER
        // =========================================================

        String createBody = """
                {
                    "name": "Workflow User",
                    "email": "workflow.user@example.com",
                    "gender": "Male",
                    "status": "Active"
                }
                """;

        Response createResponse =
                given(requestSpec)
                    .body(createBody)

                .when()
                    .post(Endpoints.CREATE_USER);

        // Print response for troubleshooting
        System.out.println("======================================");
        System.out.println("CREATE USER RESPONSE");
        System.out.println("======================================");

        System.out.println("POST URL: "
                + ConfigReader.getProperty("baseURI")
                + Endpoints.CREATE_USER);

        System.out.println("POST Status Code: "
                + createResponse.statusCode());

        System.out.println("POST Response:");
        System.out.println(createResponse.asPrettyString());

        System.out.println("======================================");


        // =========================================================
        // VALIDATE CREATE RESPONSE
        // =========================================================

        assertEquals(
                createResponse.statusCode(),
                201,
                "User creation failed"
        );

        assertEquals(
                createResponse.jsonPath().getString("name"),
                "Workflow User"
        );

        assertEquals(
                createResponse.jsonPath().getString("email"),
                "workflow.user@example.com"
        );

        assertEquals(
                createResponse.jsonPath().getString("gender"),
                "Male"
        );

        assertEquals(
                createResponse.jsonPath().getString("status"),
                "Active"
        );


        // Extract generated ID
        String userId =
                createResponse.jsonPath().getString("id");

        assertNotNull(
                userId,
                "Created user ID should not be null"
        );

        System.out.println("Created User ID: " + userId);


        // =========================================================
        // STEP 2: GET CREATED USER
        // =========================================================

        System.out.println("======================================");
        System.out.println("GET CREATED USER");
        System.out.println("======================================");

        given(requestSpec)
            .pathParam("id", userId)

        .when()
            .get(Endpoints.USER_BY_ID)

        .then()
            .statusCode(200)
            .body("id", equalTo(userId))
            .body("name", equalTo("Workflow User"))
            .body("email", equalTo("workflow.user@example.com"))
            .body("gender", equalTo("Male"))
            .body("status", equalTo("Active"));


        // =========================================================
        // STEP 3: PATCH USER
        // =========================================================

        String patchBody = """
                {
                    "status": "Inactive"
                }
                """;

        System.out.println("======================================");
        System.out.println("PATCH USER");
        System.out.println("======================================");

        given(requestSpec)
            .pathParam("id", userId)
            .body(patchBody)

        .when()
            .patch(Endpoints.PATCH_USER)

        .then()
            .statusCode(200)
            .body("id", equalTo(userId))
            .body("status", equalTo("Inactive"));


        // =========================================================
        // STEP 4: GET USER AFTER PATCH
        // =========================================================

        System.out.println("======================================");
        System.out.println("GET USER AFTER PATCH");
        System.out.println("======================================");

        given(requestSpec)
            .pathParam("id", userId)

        .when()
            .get(Endpoints.USER_BY_ID)

        .then()
            .statusCode(200)
            .body("id", equalTo(userId))
            .body("name", equalTo("Workflow User"))
            .body("email", equalTo("workflow.user@example.com"))
            .body("gender", equalTo("Male"))
            .body("status", equalTo("Inactive"));


        // =========================================================
        // STEP 5: DELETE USER
        // =========================================================

        System.out.println("======================================");
        System.out.println("DELETE USER");
        System.out.println("======================================");

        given(requestSpec)
            .pathParam("id", userId)

        .when()
            .delete(Endpoints.DELETE_USER)

        .then()
            .statusCode(200);


        // =========================================================
        // STEP 6: VERIFY USER IS DELETED
        // =========================================================

        System.out.println("======================================");
        System.out.println("VERIFY USER DELETED");
        System.out.println("======================================");

        given(requestSpec)
            .pathParam("id", userId)

        .when()
            .get(Endpoints.USER_BY_ID)

        .then()
            .statusCode(404);

        System.out.println(
                "User " + userId + " successfully deleted."
        );

        System.out.println("======================================");
        System.out.println("COMPLETE USER WORKFLOW PASSED");
        System.out.println("======================================");
    }
}