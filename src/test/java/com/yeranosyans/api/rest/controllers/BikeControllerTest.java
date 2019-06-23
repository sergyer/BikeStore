package com.yeranosyans.api.rest.controllers;

import com.yeranosyans.api.facade.bike.BikeControllerFacade;
import com.yeranosyans.api.facade.bike.model.CreateBikeModel;
import com.yeranosyans.api.facade.bike.model.UpdateBikeModel;
import com.yeranosyans.api.facade.bike.model.ViewBikeModel;
import com.yeranosyans.api.rest.controllers.bike.BikeController;
import com.yeranosyans.common.AbstractApiUnitTest;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;
import java.util.Arrays;

import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BikeController.class)
public class BikeControllerTest extends AbstractApiUnitTest {

    //region Dependencies
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @MockBean
    private BikeControllerFacade bikeControllerFacade;
    //endregion


    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .build();
    }

    //region Test cases
    @Test
    public void shouldReturnSingleBikeWhenIdIsProvided() throws Exception {
        //Test data
        final Long bikeId = 1L;
        final ViewBikeModel bike = createViewBikeModel(bikeId);
        //Mock
        given(bikeControllerFacade.getBike(eq(bike.getId()))).willReturn(bike);
        //API call
        mockMvc.perform(get("/server/api/v1/bikes/{bikeId}", bike.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.id", is(equalTo(bike.getId().intValue()))))
                .andExpect(jsonPath("$.model", is(equalTo(bike.getModel()))))
                .andExpect(jsonPath("$.name", is(equalTo(bike.getName()))))
                .andExpect(jsonPath("$.purchasePrice", is(equalTo(bike.getPurchasePrice().intValue()))))
                .andExpect(jsonPath("$.serialNumber", is(equalTo(bike.getSerialNumber()))));
        //Verify
        verify(bikeControllerFacade).getBike(eq(bikeId));
        verifyNoMoreInteractions(bikeControllerFacade);
    }


    @Test
    public void shouldReturnAllBikes() throws Exception {
        //Test data
        final Long bikeId1 = 1L;
        final Long bikeId2 = 2L;
        final ViewBikeModel bike1 = createViewBikeModel(bikeId1);
        final ViewBikeModel bike2 = createViewBikeModel(bikeId2);
        //Mock
        given(bikeControllerFacade.getAll()).willReturn(Arrays.asList(bike1, bike2));
        //API call
        mockMvc.perform(get("/server/api/v1/bikes")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$", hasSize(2)));
        //Verify
        verify(bikeControllerFacade).getAll();
        verifyNoMoreInteractions(bikeControllerFacade);
    }

    @Test
    public void shouldCreateBikeForProvidedDto() throws Exception {
        //Test data
        final CreateBikeModel createBikeModel = new CreateBikeModel(
                "Kawasaki", "Ninja", BigDecimal.TEN, "332434324234"
        );
        final ViewBikeModel bike = createViewBikeModel(5L);
        //Mock
        given(bikeControllerFacade.createBike(eq(createBikeModel))).willReturn(bike);
        //API call
        mockMvc.perform(post("/server/api/v1/bikes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(createBikeModel)))
                .andExpect(status().is(201))
                .andExpect(header().string("Location", equalTo(bike.getId().toString())));
        //Verify
        verify(bikeControllerFacade).createBike(eq(createBikeModel));
        verifyNoMoreInteractions(bikeControllerFacade);
    }

    @Test
    public void shouldUpdateBikeForProvidedDto() throws Exception {
        //Test data
        final UpdateBikeModel updateBikeModel = new UpdateBikeModel(
                "Kawasaki", "Ninja", BigDecimal.TEN, "332434324234"
        );
        final ViewBikeModel bike = createViewBikeModel(5L);
        //Mock
        given(bikeControllerFacade.updateBike(eq(bike.getId()), eq(updateBikeModel))).willReturn(bike);
        //API call
        mockMvc.perform(put("/server/api/v1/bikes/{id}", bike.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(updateBikeModel)))
                .andExpect(status().is(202));
        //Verify
        verify(bikeControllerFacade).updateBike(eq(bike.getId()), eq(updateBikeModel));
        verifyNoMoreInteractions(bikeControllerFacade);
    }

    @Test
    public void shouldRemoveBikeForProvidedId() throws Exception {
        //Test data
        final Long bikeId = 5L;
        //Mock
        doNothing().when(bikeControllerFacade).remove(eq(bikeId));
        //API call
        mockMvc.perform(delete("/server/api/v1/bikes/{id}", bikeId));
        //Verify
        verify(bikeControllerFacade).remove(eq(bikeId));
        verifyNoMoreInteractions(bikeControllerFacade);
    }

    //endregion
}
