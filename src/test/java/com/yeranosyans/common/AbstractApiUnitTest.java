package com.yeranosyans.common;

import com.yeranosyans.model.Bike;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@RunWith(SpringRunner.class)
public abstract class AbstractApiUnitTest {

    protected Bike createBike(final Long bikeId) {
        final Bike bike = new Bike();
        bike.setId(bikeId);
        bike.setPurchasePrice(BigDecimal.TEN);
        bike.setSerialNumber("131232133");
        bike.setName("Kawasaki");
        bike.setModel("Ninja");
        bike.setCreatedOn(LocalDateTime.now());
        bike.setUpdatedOn(bike.getCreatedOn());
        return bike;
    }

}
