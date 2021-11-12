package com.teciopgrid.ecommerceprice.service;

import com.teciopgrid.ecommerceprice.controller.dto.PriceCriteriaDto;
import com.teciopgrid.ecommerceprice.controller.dto.PriceResultDto;

/**
 * The interface Ecommerce price service.
 */
public interface EcommercePriceService {

    /**
     * Gets price.
     *
     * @param priceCriteriaDto the price criteria dto
     * @return the price
     */
    PriceResultDto getPrice(PriceCriteriaDto priceCriteriaDto);
}
