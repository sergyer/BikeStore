package com.yeranosyans.api.facade.bike.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ViewBikeModel {
    private Long id;
    private String name;
    private String model;
    private BigDecimal purchasePrice;
    private String serialNumber;
}
