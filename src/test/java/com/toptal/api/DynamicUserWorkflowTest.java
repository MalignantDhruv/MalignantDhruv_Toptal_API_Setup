package com.toptal.api;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.toptal.api.base.BaseTest;
import com.toptal.api.client.ApiClient;

import io.restassured.response.Response;

import static org.testng.Assert.*;

public class DynamicUserWorkflowTest extends BaseTest {

    private ApiClient apiClient;

    @BeforeClass
    public void initializeApiClient() {

        apiClient = new ApiClient(requestSpec);
    }

    @Test
    public void completeUserWorkflowTest() {

        // ==========================================
        // STEP 1: CREATE USER
        // ==========================================

        String createBody = """
                {
                    "name": "Dynamic User",
                    "email": "dynamic.user@example.com",
                    "gender": "Male",
                    "status": "Active"
                }
                """;

        Response createResponse = apiClient.createUser(createBody);

        assertEquals(
                createResponse.statusCode(),
                201,
                "User creation failed"
        );

        String userId = createResponse.jsonPath().getString("id");

        assertNotNull(
                userId,
                "Created user ID should not be null"
        );

        System.out.println("Created User ID: " + userId);


        // ==========================================
        // STEP 2: GET CREATED USER
        // ==========================================

        Response getResponse = apiClient.getUserById(userId);

        assertEquals(
                getResponse.statusCode(),
                200,
                "GET user request failed"
        );

        assertEquals(
                getResponse.jsonPath().getString("id"),
                userId
        );

        assertEquals(
                getResponse.jsonPath().getString("name"),
                "Dynamic User"
        );

        assertEquals(
                getResponse.jsonPath().getString("email"),
                "dynamic.user@example.com"
        );

        assertEquals(
                getResponse.jsonPath().getString("gender"),
                "Male"
        );

        assertEquals(
                getResponse.jsonPath().getString("status"),
                "Active"
        );

        System.out.println("Initial GET Response:");
        System.out.println(getResponse.asPrettyString());


        // ==========================================
        // STEP 3: UPDATE USER USING PUT
        // ==========================================

        String updateBody = """
                {
                    "name": "Dynamic User Updated",
                    "email": "dynamic.updated@example.com",
                    "gender": "Male",
                    "status": "Inactive"
                }
                """;

        Response updateResponse =
                apiClient.updateUser(userId, updateBody);

        assertEquals(
                updateResponse.statusCode(),
                200,
                "PUT update failed"
        );

        assertEquals(
                updateResponse.jsonPath().getString("id"),
                userId
        );

        assertEquals(
                updateResponse.jsonPath().getString("name"),
                "Dynamic User Updated"
        );

        assertEquals(
                updateResponse.jsonPath().getString("email"),
                "dynamic.updated@example.com"
        );

        assertEquals(
                updateResponse.jsonPath().getString("gender"),
                "Male"
        );

        assertEquals(
                updateResponse.jsonPath().getString("status"),
                "Inactive"
        );

        System.out.println("PUT Update Response:");
        System.out.println(updateResponse.asPrettyString());


        // ==========================================
        // STEP 4: GET AFTER PUT
        // ==========================================

        Response updatedGetResponse =
                apiClient.getUserById(userId);

        assertEquals(
                updatedGetResponse.statusCode(),
                200,
                "GET after PUT failed"
        );

        assertEquals(
                updatedGetResponse.jsonPath().getString("name"),
                "Dynamic User Updated"
        );

        assertEquals(
                updatedGetResponse.jsonPath().getString("email"),
                "dynamic.updated@example.com"
        );

        assertEquals(
                updatedGetResponse.jsonPath().getString("status"),
                "Inactive"
        );

        System.out.println("GET After PUT Response:");
        System.out.println(updatedGetResponse.asPrettyString());


        // ==========================================
        // STEP 5: PATCH USER
        // ==========================================

        String patchBody = """
                {
                    "status": "Active"
                }
                """;

        Response patchResponse =
                apiClient.patchUser(userId, patchBody);

        assertEquals(
                patchResponse.statusCode(),
                200,
                "PATCH update failed"
        );

        assertEquals(
                patchResponse.jsonPath().getString("id"),
                userId
        );

        assertEquals(
                patchResponse.jsonPath().getString("status"),
                "Active"
        );

        System.out.println("PATCH Response:");
        System.out.println(patchResponse.asPrettyString());


        // ==========================================
        // STEP 6: GET AFTER PATCH
        // ==========================================

        Response patchedGetResponse =
                apiClient.getUserById(userId);

        assertEquals(
                patchedGetResponse.statusCode(),
                200,
                "GET after PATCH failed"
        );

        assertEquals(
                patchedGetResponse.jsonPath().getString("id"),
                userId
        );

        assertEquals(
                patchedGetResponse.jsonPath().getString("name"),
                "Dynamic User Updated"
        );

        assertEquals(
                patchedGetResponse.jsonPath().getString("email"),
                "dynamic.updated@example.com"
        );

        assertEquals(
                patchedGetResponse.jsonPath().getString("gender"),
                "Male"
        );

        assertEquals(
                patchedGetResponse.jsonPath().getString("status"),
                "Active"
        );

        System.out.println("GET After PATCH Response:");
        System.out.println(patchedGetResponse.asPrettyString());


        // ==========================================
        // STEP 7: DELETE USER
        // ==========================================

        Response deleteResponse =
                apiClient.deleteUser(userId);

        assertEquals(
                deleteResponse.statusCode(),
                200,
                "DELETE user failed"
        );

        System.out.println("User deleted successfully: " + userId);


        // ==========================================
        // STEP 8: VERIFY USER IS DELETED
        // ==========================================

        Response deletedUserResponse =
                apiClient.getUserById(userId);

        assertEquals(
                deletedUserResponse.statusCode(),
                404,
                "Deleted user should not be found"
        );

        System.out.println("GET after DELETE returned: "
                + deletedUserResponse.statusCode());

        System.out.println("======================================");
        System.out.println("COMPLETE CRUD WORKFLOW PASSED");
        System.out.println("======================================");
    }
}