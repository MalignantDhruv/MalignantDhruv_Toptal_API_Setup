package com.toptal.api;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.toptal.api.base.BaseTest;
import com.toptal.api.client.ApiClient;

import io.restassured.response.Response;

import static org.testng.Assert.*;

public class DeleteUserTest extends BaseTest {

    private ApiClient apiClient;

    @BeforeClass
    public void initializeApiClient() {
        apiClient = new ApiClient(requestSpec);
    }

    @Test
    public void deleteUserTest() {

        String requestBody = """
                {
                    "name": "Delete Test User",
                    "email": "delete.test@example.com",
                    "gender": "Male",
                    "status": "Active"
                }
                """;

        // Create user
        Response createResponse = apiClient.createUser(requestBody);

        assertEquals(createResponse.statusCode(), 201,
                "User creation failed");

        String userId = createResponse.jsonPath().getString("id");

        assertNotNull(userId,
                "Created user ID should not be null");

        System.out.println("Created User ID: " + userId);

        // Delete user
        Response deleteResponse = apiClient.deleteUser(userId);

        assertEquals(deleteResponse.statusCode(), 200,
                "User deletion failed");

        System.out.println("Deleted User ID: " + userId);

        // Verify user is deleted
        Response getResponse = apiClient.getUserById(userId);

        assertEquals(getResponse.statusCode(), 404,
                "Deleted user should not be found");

        System.out.println("GET after DELETE returned: "
                + getResponse.statusCode());
    }
}