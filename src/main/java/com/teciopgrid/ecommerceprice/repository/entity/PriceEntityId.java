package com.teciopgrid.ecommerceprice.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PriceEntityId implements Serializable {

    private Integer brandId;
    private Long startDate;
    private Long endDate;
    private Integer productId;
}
