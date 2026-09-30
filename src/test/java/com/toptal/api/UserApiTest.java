package com.toptal.api;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.toptal.api.base.BaseTest;
import com.toptal.api.client.ApiClient;

import io.restassured.response.Response;

import static org.testng.Assert.*;

public class UserApiTest extends BaseTest {

    private ApiClient apiClient;

    @BeforeClass
    public void initializeApiClient() {
        apiClient = new ApiClient(requestSpec);
    }

    @Test
    public void getUserTest() {

        String requestBody = """
                {
                    "name": "GET Test User",
                    "email": "get.test@example.com",
                    "gender": "Male",
                    "status": "Active"
                }
                """;

        // Create test user
        Response createResponse = apiClient.createUser(requestBody);

        assertEquals(createResponse.statusCode(), 201,
                "User creation failed");

        String userId = createResponse.jsonPath().getString("id");

        assertNotNull(userId,
                "Created user ID should not be null");

        // GET user
        Response getResponse = apiClient.getUserById(userId);

        assertEquals(getResponse.statusCode(), 200,
                "GET user request failed");

        assertEquals(getResponse.jsonPath().getString("id"), userId);
        assertEquals(getResponse.jsonPath().getString("name"),
                "GET Test User");
        assertEquals(getResponse.jsonPath().getString("email"),
                "get.test@example.com");
        assertEquals(getResponse.jsonPath().getString("gender"),
                "Male");
        assertEquals(getResponse.jsonPath().getString("status"),
                "Active");

        System.out.println("GET User Response:");
        System.out.println(getResponse.asPrettyString());

        // Cleanup
        Response deleteResponse = apiClient.deleteUser(userId);

        assertEquals(deleteResponse.statusCode(), 200,
                "Test user could not be deleted");

        System.out.println("Test user deleted: " + userId);
    }
}