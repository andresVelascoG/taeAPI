package com.globant.automation.tests;

import com.globant.automation.config.TestRunner;
import com.globant.automation.model.CreateUserDTO;
import com.globant.automation.model.CreateUserResponseDTO;
import com.globant.automation.request.RequestBuilder;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class PostUserTest extends TestRunner {
    @Test(testName = "Validate user creation")
    public void createUserTest(){
        CreateUserDTO createUserDTO = CreateUserDTO.builder()
                .id(1)
                .username("Andrew")
                .firstName("Andres")
                .lastName("Alvaro")
                .email("andresa@example.com")
                .password("securePassword123")
                .phone("314567890")
                .userStatus(1)
                .build();

        Response response = RequestBuilder.postRequest( getBaseUrl(), "/user", createUserDTO);
        CreateUserResponseDTO createUserResponseDTO = response.as(CreateUserResponseDTO.class);

        assertEquals(response.getStatusCode(), 200, "The status code doesn't match");
        assertEquals(createUserResponseDTO.getCode(), 200, "The job should  match");
        assertEquals(createUserResponseDTO.getType(), "unknown", "The name should match");
        assertNotNull(createUserResponseDTO.getMessage(), "The createdAt should not be null");
    }
}
