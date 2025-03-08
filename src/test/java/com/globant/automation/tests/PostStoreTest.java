package com.globant.automation.tests;

import com.globant.automation.config.TestRunner;
import com.globant.automation.model.CreateUserDTO;
import com.globant.automation.model.CreateUserResponseDTO;
import com.globant.automation.model.PlaceOrderDTO;
import com.globant.automation.model.PlaceOrderResponseDTO;
import com.globant.automation.request.RequestBuilder;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class PostStoreTest extends TestRunner {



    @Test(testName = "Validate order creation")
    public void createOrderTest(){
        int petOrdered = 1;

        PlaceOrderDTO placeOrderDTO = PlaceOrderDTO.builder()
                .id(0)
                .petId(petOrdered)
                .quantity(1)
                .shipDate("2025-03-08T17:02:36.501Z")
                .status("placed")
                .complete(true)
                .build();

        Response response = RequestBuilder.postRequest( getBaseUrl(), "/store/order", placeOrderDTO);
        PlaceOrderResponseDTO placeOrderResponseDTO = response.as(PlaceOrderResponseDTO.class);

        assertEquals(response.getStatusCode(), 200, "The status code doesn't match");
        assertNotNull(placeOrderResponseDTO.getId(), "The id should be missing");
        assertEquals(placeOrderResponseDTO.getPetId(), petOrdered, "The pet id should match");
        assertEquals(placeOrderResponseDTO.getQuantity(), 1, "The quantity should match");
        assertNotNull(placeOrderResponseDTO.getShipDate(), "The createdAt should not be null");
        assertEquals(placeOrderResponseDTO.getStatus(), "placed", "The status should match");
        assertTrue(placeOrderResponseDTO.getComplete(), "The complete of the order should be true");
    }
}
