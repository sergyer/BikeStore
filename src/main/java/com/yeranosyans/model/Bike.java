package com.yeranosyans.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@EqualsAndHashCode(of = {"name","model"})
@Entity
public class Bike implements Serializable {
    //region Properties
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String model;
    private BigDecimal purchasePrice;
    private String serialNumber;
    private LocalDateTime createdOn;
    private LocalDateTime updatedOn;
    //endregion
}
