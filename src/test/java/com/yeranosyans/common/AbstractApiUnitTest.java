package com.yeranosyans.common;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yeranosyans.api.facade.bike.model.ViewBikeModel;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

@RunWith(SpringRunner.class)
public abstract class AbstractApiUnitTest {

    //region Helper methods
    protected ViewBikeModel createViewBikeModel(final Long bikeId) {
        final ViewBikeModel bike = new ViewBikeModel();
        bike.setId(bikeId);
        bike.setPurchasePrice(BigDecimal.TEN);
        bike.setSerialNumber("131232133");
        bike.setName("Kawasaki");
        bike.setModel("Ninja");
        return bike;
    }


    protected static String asJsonString(final Object obj) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    //endregion


}
