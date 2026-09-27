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

        String requestBody = """
                {
                    "name": "Arjun Mehta",
                    "email": "arjun.mehta@example.com",
                    "gender": "Male",
                    "status": "Active"
                }
                """;

        Response response = apiClient.createUser(requestBody);

        assertEquals(response.statusCode(), 201);

        assertEquals(
                response.jsonPath().getString("name"),
                "Arjun Mehta"
        );

        assertEquals(
                response.jsonPath().getString("email"),
                "arjun.mehta@example.com"
        );

        assertEquals(
                response.jsonPath().getString("gender"),
                "Male"
        );

        assertEquals(
                response.jsonPath().getString("status"),
                "Active"
        );

        assertNotNull(
                response.jsonPath().getString("id"),
                "Created user ID should not be null"
        );

        System.out.println("Created User:");
        System.out.println(response.asPrettyString());
    }
}