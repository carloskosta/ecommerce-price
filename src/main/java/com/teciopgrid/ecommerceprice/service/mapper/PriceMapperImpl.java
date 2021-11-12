package com.teciopgrid.ecommerceprice.service.mapper;

import com.teciopgrid.ecommerceprice.controller.dto.PriceCriteriaDto;
import com.teciopgrid.ecommerceprice.controller.dto.PriceResultDto;
import com.teciopgrid.ecommerceprice.repository.entity.PriceEntity;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.TimeZone;

@Component
public class PriceMapperImpl implements PriceMapper {

    private static final String DATE_PATTERN = "yyyy-MM-dd HH:mm:ss";

    @Override
    public PriceEntity mapPriceDtoAsPriceEntity(final PriceCriteriaDto priceCriteriaDto) {
        return PriceEntity.builder()
                .applyDate(LocalDateTime.parse(priceCriteriaDto.getApplyDate(),
                                DateTimeFormatter.ofPattern(DATE_PATTERN))
                        .atZone(ZoneId.systemDefault())
                        .toEpochSecond())
                .brandId(priceCriteriaDto.getBrandId())
                .productId(priceCriteriaDto.getProductId()).build();
    }

    @Override
    public PriceResultDto mapPriceEntityAsPriceDto(final PriceEntity entity) {
        return new PriceResultDto()
                .price(entity.getPrice())
                .brandId(entity.getBrandId())
                .productId(entity.getProductId())
                .priceList(entity.getPriceList())
                .applyDateStart(LocalDateTime.ofInstant(Instant.ofEpochSecond(entity.getStartDate()),
                        TimeZone.getDefault().toZoneId()).toString())
                .applyDateEnd(LocalDateTime.ofInstant(Instant.ofEpochSecond(entity.getEndDate()),
                        TimeZone.getDefault().toZoneId()).toString());
    }
}
