package com.toptal.api;

import static org.testng.Assert.*;

import org.testng.annotations.Test;

import com.toptal.api.base.BaseTest;

import static io.restassured.RestAssured.*;

public class InterviewPracticeTest extends BaseTest {

    @Test
    public void getusertest() {

        String name = given(requestSpec)
                .when()
                .get("/users/1")
                .then()
                .statusCode(200)
                .extract()
                .jsonPath()
                .getString("name");

        System.out.println("User Name: " + name);

        assertNotNull(name);
        assertFalse(name.isEmpty());
        
        String email = given(requestSpec)
                .when()
                .get("/users/5")
                .then()
                .statusCode(200)
                .extract()
                .jsonPath()
                .getString("email");

        System.out.println("User Email id: " + email);

        assertNotNull(email);
        assertFalse(email.isEmpty());
        
        String gender = given(requestSpec)
                .when()
                .get("/users/8")
                .then()
                .statusCode(200)
                .extract()
                .jsonPath()
                .getString("gender");

        System.out.println("User Gender: " + gender);

        assertNotNull(gender);
        assertFalse(gender.isEmpty());
        
        String status = given(requestSpec)
                .when()
                .get("/users/11")
                .then()
                .statusCode(200)
                .extract()
                .jsonPath()
                .getString("status");

        System.out.println("User status: " + status);

        assertNotNull(status);
        assertFalse(status.isEmpty());
    }
}