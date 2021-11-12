package com.teciopgrid.ecommerceprice.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Builder
@Table(name = "PRICE")
@IdClass(PriceEntityId.class)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PriceEntity {

    @Id
    @Column(name = "BRAND_ID", nullable = false)
    private Integer brandId;
    @Id
    @Column(name = "START_DATE", nullable = false)
    private Long startDate;
    @Id
    @Column(name = "END_DATE", nullable = false)
    private Long endDate;
    @Column(name = "PRICE_LIST", nullable = false)
    private int priceList;
    @Id
    @Column(name = "PRODUCT_ID", nullable = false)
    private Integer productId;
    @Column(name = "PRIORITY", nullable = false)
    private int priority;
    @Column(name = "PRICE", nullable = false)
    private BigDecimal price;
    @Column(name = "CURR", nullable = false)
    private String currency;
    @Transient
    private long applyDate;
}
