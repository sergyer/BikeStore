package com.yeranosyans.bike.model.dto;

import com.yeranosyans.bike.model.Bike;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
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
