package com.yeranosyans.api.facade.bike.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CreateBikeModel {

    private final String name;
    private final String model;
    private final BigDecimal purchasePrice;
    private final String serialNumber;
}

