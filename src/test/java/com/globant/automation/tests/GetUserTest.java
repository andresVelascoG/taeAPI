package com.globant.automation.tests;

import io.restassured.RestAssured;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.equalTo;

public class GetUserTest {
    @Test(testName = "Validate user found")
    public void userFoundTestAssertion(){
        RestAssured.given()
                .baseUri("https://reqres.in/api")
                .header("Content-type", "application/json")
                .when()
                .get("/users/2")
                .then()
                .body("data.id",equalTo(2))
                .body("data.first_name", equalTo("Janet"))
                .body("data.last_name", equalTo("Weaver"));
    }
}
