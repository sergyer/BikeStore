package com.yeranosyans.bike.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@EqualsAndHashCode(of = {"name","model"})
public class Bike implements Serializable {
    //region Properties
    private String name;
    private String model;
    private BigDecimal purchasePrice;
    private String serialNumber;
    private LocalDateTime createdOn;
    private LocalDateTime updatedOn;
    //endregion
}
