package com.teciopgrid.ecommerceprice.service;

import com.teciopgrid.ecommerceprice.controller.dto.PriceCriteriaDto;
import com.teciopgrid.ecommerceprice.controller.dto.PriceResultDto;
import com.teciopgrid.ecommerceprice.exception.NotFoundEcommerceEntityException;
import com.teciopgrid.ecommerceprice.exception.NotFoundEcommercePriceCriteriaDtoException;
import com.teciopgrid.ecommerceprice.repository.EcommercePriceRepository;
import com.teciopgrid.ecommerceprice.repository.entity.PriceEntity;
import com.teciopgrid.ecommerceprice.service.mapper.PriceMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EcommercePriceServiceImpl implements EcommercePriceService {

    private final PriceMapper priceMapper;
    private final EcommercePriceRepository ecommercePriceRepository;

    @Override
    public PriceResultDto getPrice(final PriceCriteriaDto priceCriteriaDto) {
        var priceEntity = Optional.ofNullable(priceCriteriaDto)
                .map(priceCriteria -> priceMapper.mapPriceDtoAsPriceEntity(priceCriteriaDto))
                .orElseThrow(NotFoundEcommercePriceCriteriaDtoException::new);

        var priceByProductAndBrand = ecommercePriceRepository.findPriceByProductAndBrand(priceEntity);

        return priceByProductAndBrand.stream()
                .max(Comparator.comparingInt(PriceEntity::getPriority))
                .map(priceMapper::mapPriceEntityAsPriceDto)
                .orElseThrow(NotFoundEcommerceEntityException::new);
    }
}
