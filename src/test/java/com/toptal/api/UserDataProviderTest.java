package com.toptal.api;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.toptal.api.base.BaseTest;
import com.toptal.api.constants.Endpoints;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class UserDataProviderTest extends BaseTest {

    @DataProvider(name = "userData")
    public Object[][] userData() {

        return new Object[][] {
            {"Dhruv Mehta", "dhruv.mehta@example.com", "Male", "Active"},
            {"Rahul Sharma", "rahul.sharma@example.com", "Male", "Active"},
            {"Priya Patel", "priya.patel@example.com", "Female", "Active"},
            {"Amit Shah", "amit.shah@example.com", "Male", "Inactive"},
            {"Neha Patel", "neha.patel@example.com", "Female", "Active"}
        };
    }

    @Test(dataProvider = "userData")
    public void verifyUserData(
            String expectedName,
            String expectedEmail,
            String expectedGender,
            String expectedStatus) {

        given(requestSpec)
            .queryParam("email", expectedEmail)

        .when()
            .get(Endpoints.USERS)

        .then()
            .statusCode(200)
            .body("find { it.email == '" + expectedEmail + "' }.name",
                    equalTo(expectedName))
            .body("find { it.email == '" + expectedEmail + "' }.gender",
                    equalTo(expectedGender))
            .body("find { it.email == '" + expectedEmail + "' }.status",
                    equalTo(expectedStatus));
    }
}