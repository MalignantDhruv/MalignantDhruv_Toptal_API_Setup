package com.toptal.api;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.toptal.api.base.BaseTest;
import com.toptal.api.client.ApiClient;

import io.restassured.response.Response;

import static org.testng.Assert.*;

public class CreateUserTest extends BaseTest {

    private ApiClient apiClient;

    @BeforeClass
    public void initializeApiClient() {

        apiClient = new ApiClient(requestSpec);
    }

    @Test
    public void createUserTest() {

        // ==========================================
        // STEP 1: CREATE USER
        // ==========================================

        String requestBody = """
                {
                    "name": "Arjun Mehta",
                    "email": "arjun.mehta@example.com",
                    "gender": "Male",
                    "status": "Active"
                }
                """;

        Response createResponse = apiClient.createUser(requestBody);

        assertEquals(
                createResponse.statusCode(),
                201,
                "User creation failed"
        );

        // ==========================================
        // STEP 2: VALIDATE CREATED USER
        // ==========================================

        assertEquals(
                createResponse.jsonPath().getString("name"),
                "Arjun Mehta"
        );

        assertEquals(
                createResponse.jsonPath().getString("email"),
                "arjun.mehta@example.com"
        );

        assertEquals(
                createResponse.jsonPath().getString("gender"),
                "Male"
        );

        assertEquals(
                createResponse.jsonPath().getString("status"),
                "Active"
        );

        // ==========================================
        // STEP 3: EXTRACT GENERATED ID
        // ==========================================

        String userId =
                createResponse.jsonPath().getString("id");

        assertNotNull(
                userId,
                "Created user ID should not be null"
        );

        System.out.println("Created User ID: " + userId);

        System.out.println("Created User:");
        System.out.println(createResponse.asPrettyString());

        // ==========================================
        // STEP 4: DELETE CREATED USER
        // ==========================================

        Response deleteResponse =
                apiClient.deleteUser(userId);

        assertEquals(
                deleteResponse.statusCode(),
                200,
                "Created user could not be deleted"
        );

        System.out.println("Deleted User ID: " + userId);

        // ==========================================
        // STEP 5: VERIFY USER IS DELETED
        // ==========================================

        Response getDeletedUserResponse =
                apiClient.getUserById(userId);

        assertEquals(
                getDeletedUserResponse.statusCode(),
                404,
                "Deleted user should not be found"
        );

        System.out.println(
                "GET after DELETE returned: "
                + getDeletedUserResponse.statusCode()
        );

        System.out.println("======================================");
        System.out.println("CREATE → VALIDATE → DELETE → VERIFY PASSED");
        System.out.println("======================================");
    }
}