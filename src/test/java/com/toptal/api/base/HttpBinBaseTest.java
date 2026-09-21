package com.toptal.api.base;

import org.testng.annotations.BeforeClass;

import com.toptal.api.config.ConfigReader;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class HttpBinBaseTest {

    protected RequestSpecification requestSpec;

    @BeforeClass
    public void setUp() {

        requestSpec = new RequestSpecBuilder()
                .setBaseUri(ConfigReader.getProperty("authBaseURI"))
                .setContentType(ContentType.JSON)
                .build();
    }
}