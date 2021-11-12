package com.teciopgrid.ecommerceprice.service.mapper;

import com.teciopgrid.ecommerceprice.controller.dto.PriceCriteriaDto;
import com.teciopgrid.ecommerceprice.controller.dto.PriceResultDto;
import com.teciopgrid.ecommerceprice.repository.entity.PriceEntity;

/**
 * The interface Price mapper.
 */
public interface PriceMapper {

    /**
     * Map price dto as price entity price entity.
     *
     * @param priceCriteriaDto the price criteria dto
     * @return the price entity
     */
    PriceEntity mapPriceDtoAsPriceEntity(final PriceCriteriaDto priceCriteriaDto);

    /**
     * Map price entity as price dto price result dto.
     *
     * @param entity the entity
     * @return the price result dto
     */
    PriceResultDto mapPriceEntityAsPriceDto(final PriceEntity entity);

}
