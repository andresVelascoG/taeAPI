package com.globant.automation.tests;

import com.globant.automation.config.TestRunner;
import com.globant.automation.model.GetPetResponseDTO;
import com.globant.automation.model.LoginUserResponseDTO;
import com.globant.automation.request.RequestBuilder;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

import static org.testng.Assert.*;

public class GetPetsTest extends TestRunner {

    @Test(testName = "Validate get a pet")
    public void petGetTestAssertion() {
        int petID=2;
        Response response = RequestBuilder.getRequest(getBaseUrl(), "/pet/"+petID);

        GetPetResponseDTO getPetResponseDTO = response.as(GetPetResponseDTO.class);

        assertEquals(response.getStatusCode(), 200, "The status code doesn't match.");
        assertEquals(getPetResponseDTO.getId(), petID, "The id should match");
        assertEquals(getPetResponseDTO.getCategory().getId(), 0, "The category id should match");
        assertEquals(getPetResponseDTO.getName(), "Tom", "The name of the pet should coincide");
        assertNotNull(getPetResponseDTO.getPhotoUrls(), "Photo URLs should not be null");
        assertEquals(getPetResponseDTO.getStatus(), "available", "The status should be available");
    }

    @Test(testName = "Validate the list of pets by status available")
    public void petFindByStatusTestAssertion() {
        Response response = RequestBuilder.getRequest(getBaseUrl(), "/pet/findByStatus?status=available");

        List<Map<String, Object>> petListResponse = response.jsonPath().getList("$");


        assertEquals(response.getStatusCode(), 200, "The status code doesn't match.");
        assertTrue(petListResponse.size() > 5, "The list should be longer than 5");


    }
}
