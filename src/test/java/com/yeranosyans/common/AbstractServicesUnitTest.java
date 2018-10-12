package com.yeranosyans.common;

import com.yeranosyans.model.Bike;
import com.yeranosyans.model.dto.BikeDto;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;

@Ignore
@RunWith(MockitoJUnitRunner.class)
public abstract class AbstractServicesUnitTest {

    protected Bike createBike(final Long bikeId) {
        final Bike bike = new Bike();
        bike.setModel("Kawasaki");
        bike.setName("Ninja");
        bike.setPurchasePrice(BigDecimal.TEN);
        bike.setId(bikeId);
        bike.setSerialNumber("3434343");
        return bike;
    }

    protected BikeDto createBikeDto() {
        return new BikeDto("Kawasaki", "Ninja", BigDecimal.TEN, "12312312");
    }
}
