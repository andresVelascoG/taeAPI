package com.globant.automation.tests;

import com.globant.automation.config.TestRunner;
import com.globant.automation.model.CreateUserDTO;
import com.globant.automation.model.GetUserResponseDTO;
import com.globant.automation.model.LoginUserResponseDTO;
import com.globant.automation.model.UserDTO;
import com.globant.automation.request.RequestBuilder;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.testng.Assert.*;

@Slf4j
public class LoginUserTest extends TestRunner {

    private final String USERNAME = "AndrewAs";
    private final String PASSWORD = "securePassword123";

    @BeforeClass
    public void setupData() {

        CreateUserDTO createUserDTO = CreateUserDTO.builder()
                .id(69)
                .username(this.USERNAME)
                .firstName("Andres")
                .lastName("Astro")
                .email("AndrewAs@example.com")
                .password(this.PASSWORD)
                .phone("3114567890")
                .userStatus(1)
                .build();

        Response response = RequestBuilder.postRequest(getBaseUrl(),"/user", createUserDTO);

        assertEquals(response.getStatusCode(), 200, "The status code doesn't match");

        if (response.getStatusCode() != 200) {
            throw new RuntimeException("Error creating the user " + response.getStatusCode());
        }
    }

    @Test(testName = "Validate user login")
    public void userLoginTestAssertion() {
        Response response = RequestBuilder.getRequest(getBaseUrl(), "/user/login?username="+USERNAME+"&password="+PASSWORD);

        LoginUserResponseDTO loginUserResponseDTO = response.as(LoginUserResponseDTO.class);

        assertEquals(response.getStatusCode(), 200, "The status code doesn't match.");
        assertEquals(loginUserResponseDTO.getCode(), 200, "The code should match");
        assertEquals(loginUserResponseDTO.getType(), "unknown", "The type should match");
        assertNotNull(loginUserResponseDTO.getMessage(), "The message shouldn't be missing");

    }

}
