package com.yeranosyans.model.dto;

import com.yeranosyans.model.Bike;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class BikeDto {

    //region Properties
    private final String name;
    private final String model;
    private final BigDecimal purchasePrice;
    private final String serialNumber;
    //endregion

    //region Public methods
    public void updateDomainEntityProperties(final Bike bike) {
        bike.setModel(this.model);
        bike.setName(this.name);
        bike.setPurchasePrice(this.purchasePrice);
        bike.setSerialNumber(this.serialNumber);
    }
    //endregion
}
