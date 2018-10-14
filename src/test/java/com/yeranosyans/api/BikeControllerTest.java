package com.yeranosyans.api;

import com.yeranosyans.common.AbstractApiUnitTest;
import com.yeranosyans.model.Bike;
import com.yeranosyans.service.BikeService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BikeController.class)
public class BikeControllerTest extends AbstractApiUnitTest {

    //region Dependencies
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BikeService bikeService;
    //endregion


    //region Test cases
    @Test
    public void shouldReturnSingleBikeWhenIdIsProvided() throws Exception {
        //Test data
        final Long bikeId = 1L;
        final Bike bike = createBike(bikeId);
        //Mock
        given(bikeService.getById(eq(bike.getId()))).willReturn(bike);
        //API call
        mockMvc.perform(get("/api/v1/bikes/{bikeId}", bike.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.id", is(equalTo(bike.getId().intValue()))))
                .andExpect(jsonPath("$.model", is(equalTo(bike.getModel()))))
                .andExpect(jsonPath("$.name", is(equalTo(bike.getName()))))
                .andExpect(jsonPath("$.purchasePrice", is(equalTo(bike.getPurchasePrice().intValue()))))
                .andExpect(jsonPath("$.serialNumber", is(equalTo(bike.getSerialNumber()))));
        //Verify
        verify(bikeService).getById(eq(bikeId));
        verifyNoMoreInteractions(bikeService);
    }


    @Test
    public void shouldReturnAllBikes() throws Exception {
        //Test data
        final Long bikeId1 = 1L;
        final Long bikeId2 = 2L;
        final Bike bike1 = createBike(bikeId1);
        final Bike bike2 = createBike(bikeId2);
        //Mock
        given(bikeService.getAll()).willReturn(Arrays.asList(bike1, bike2));
        //API call
        mockMvc.perform(get("/api/v1/bikes")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$", hasSize(2)));
        //Verify
        verify(bikeService).getAll();
        verifyNoMoreInteractions(bikeService);
    }

    //endregion
}
