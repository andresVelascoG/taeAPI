package com.globant.automation.request;

import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RequestBuilder {
    public static Response getRequest(String baseUrl, String path){
        RequestSpecification requestSpecification = RestAssured.given()
                .baseUri(baseUrl)
                .header("Content-Type","application/json");
        return requestSpecification.get(path);
    }

    public static Response postRequest(String baseUrl, String path, Object body){
        RequestSpecification requestSpecification = RestAssured.given()
                .baseUri(baseUrl)
                .header("Content-Type","application/json")
                .filter(new RequestLoggingFilter())
                .body(body);
        return  requestSpecification.post(path);
    }
}
