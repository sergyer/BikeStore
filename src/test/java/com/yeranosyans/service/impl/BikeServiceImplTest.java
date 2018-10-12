package com.yeranosyans.service.impl;

import com.yeranosyans.common.AbstractServicesUnitTest;
import com.yeranosyans.model.Bike;
import com.yeranosyans.model.dto.BikeDto;
import com.yeranosyans.persistence.BikeRepository;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

public class BikeServiceImplTest extends AbstractServicesUnitTest {

    @InjectMocks
    private BikeServiceImpl bikeService;

    @Mock
    private BikeRepository bikeRepository;

    //region Test cases

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenBikeIdIsNull() {
        final Long bikeId = null;
        bikeService.getById(bikeId);
    }

    @Test
    public void shouldReturnBikeForProvidedId() {
        //Test data
        final Long bikeId = 2L;
        final Bike bike = createBike(bikeId);
        //Mock
        when(bikeRepository.findById(eq(bikeId))).thenReturn(Optional.of(bike));
        //Service call
        final Bike result = bikeService.getById(bikeId);
        //Verify
        verify(bikeRepository).findById(eq(bikeId));
        verifyNoMoreInteractions(bikeRepository);
        //Assert
        assertThat(result).isEqualTo(bike).isSameAs(bike);
    }


    @Test(expected = RuntimeException.class)
    public void shouldThrowExceptionWhenBikeIsNotFound() {
        //Test data
        final Long id = 3L;
        //Mock
        when(bikeRepository.findById(eq(id))).thenReturn(Optional.empty());
        //Service call
        bikeService.getById(id);
    }

    @Test
    public void shouldUpdateBikeForProvidedDto() {
        //Test data
        final Long bikeId = 5L;
        final Bike bike = createBike(bikeId);
        final BikeDto bikeDto = createBikeDto();
        //Mock
        when(bikeRepository.findById(eq(bikeId))).thenReturn(Optional.of(bike));
        when(bikeRepository.save(isA(Bike.class))).then(invocationOnMock -> invocationOnMock.getArgument(0));
        //Service call
        final Bike result = bikeService.update(bikeId, bikeDto);
        //Verify
        verify(bikeRepository).findById(eq(bikeId));
        verify(bikeRepository).save(isA(Bike.class));
        verifyNoMoreInteractions(bikeRepository);
        //Assert
        assertThat(result).isEqualToIgnoringGivenFields(bikeDto, "id", "createdOn", "updatedOn");
        assertThat(result.getUpdatedOn()).isNotNull();
    }

    @Test
    public void shouldRemoveBikeWithProvidedId() {
        //Test data
        final Long bikeId = 5L;
        final Bike bike = createBike(bikeId);
        //Mock
        when(bikeRepository.findById(eq(bikeId))).thenReturn(Optional.of(bike));
        doNothing().when(bikeRepository).delete(eq(bike));
        //Service call
        bikeService.remove(bikeId);
        //Verify
        bikeRepository.findById(eq(bikeId));
        bikeRepository.delete(eq(bike));
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenBikeDtoIsNull() {
        final BikeDto bikeDto = null;
        bikeService.create(bikeDto);
    }

    @Test
    public void shouldCreateBikeForProvidedDto() {
        //Test data
        final BikeDto bikeDto = createBikeDto();
        //Mock
        when(bikeRepository.save(isA(Bike.class))).then(invocationOnMock -> invocationOnMock.getArgument(0));
        //Service call
        final Bike result = bikeService.create(bikeDto);
        //Verify
        verify(bikeRepository).save(isA(Bike.class));
        verifyNoMoreInteractions(bikeRepository);
        //Assert
        assertThat(result).isEqualToIgnoringGivenFields(bikeDto, "id", "createdOn", "updatedOn");
        assertThat(result.getUpdatedOn()).isEqualTo(result.getCreatedOn());
    }

    @Test
    public void shouldReturnListOfBikes() {
        //Test data
        final List<Bike> bikes = Arrays.asList(createBike(1L), createBike(2L));
        //Mock
        when(bikeRepository.findAll()).thenReturn(bikes);
        //Service call
        final List<Bike> result = bikeService.getAll();
        //Verify
        verify(bikeRepository).findAll();
        verifyNoMoreInteractions(bikeRepository);
        //Assert
        assertThat(result).isEqualTo(result);
    }
    //endregion

}