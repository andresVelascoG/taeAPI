package com.globant.automation.tests;

import com.globant.automation.config.TestRunner;
import com.globant.automation.model.CreateUserDTO;
import com.globant.automation.model.LoginUserResponseDTO;
import com.globant.automation.model.LogoutUserResponseDTO;
import com.globant.automation.request.RequestBuilder;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

public class LogoutUserTest extends TestRunner {

    private String sessionToken;

    @BeforeClass
    public void setupData() {
        String USERNAME = "AndrewAs";
        String PASSWORD = "securePassword123";

        CreateUserDTO createUserDTO = CreateUserDTO.builder()
                .id(69)
                .username(USERNAME)
                .firstName("Andres")
                .lastName("Astro")
                .email("AndrewAs@example.com")
                .password(PASSWORD)
                .phone("3114567890")
                .userStatus(1)
                .build();

        Response createUserResponse = RequestBuilder.postRequest(getBaseUrl(),"/user", createUserDTO);

        assertEquals(createUserResponse.getStatusCode(), 200, "The status code doesn't match");

        if (createUserResponse.getStatusCode() != 200) {
            throw new RuntimeException("Error creating the user " + createUserResponse.getStatusCode());
        }

        Response loginResponse = RequestBuilder.getRequest(getBaseUrl(), "/user/login?username="+USERNAME+"&password="+PASSWORD);

        LoginUserResponseDTO loginUserResponseDTO = loginResponse.as(LoginUserResponseDTO.class);
        assertEquals(loginResponse.getStatusCode(), 200, "The status code doesn't match.");
        if (loginResponse.getStatusCode() != 200) {
            throw new RuntimeException("Error login the user " + loginResponse.getStatusCode());
        }
        this.sessionToken=loginUserResponseDTO.getMessage();

    }

    @Test(testName = "Validate user log out")
    public void userLogoutTestAssertion() {
        Response response = RequestBuilder.getRequest(getBaseUrl(), "/user/logout");

        LogoutUserResponseDTO logoutUserResponseDTO = response.as(LogoutUserResponseDTO.class);

        assertEquals(response.getStatusCode(), 200, "The status code doesn't match.");
        assertEquals(logoutUserResponseDTO.getCode(), 200, "The code should match");
        assertEquals(logoutUserResponseDTO.getType(), "unknown", "The type should match");
        assertEquals(logoutUserResponseDTO.getMessage(), "ok");

    }

}
