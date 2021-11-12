package com.teciopgrid.ecommerceprice.service;

import com.teciopgrid.ecommerceprice.controller.dto.PriceCriteriaDto;
import com.teciopgrid.ecommerceprice.repository.EcommercePriceRepository;
import com.teciopgrid.ecommerceprice.repository.entity.PriceEntity;
import com.teciopgrid.ecommerceprice.service.mapper.PriceMapperImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(
        classes = {EcommercePriceServiceImpl.class, PriceMapperImpl.class})
class EcommercePriceServiceImplTest {

    @Autowired
    EcommercePriceServiceImpl ecommercePriceService;

    @MockBean
    EcommercePriceRepository ecommercePriceRepository;

    @Test
    void shouldReturnPriceEntityMaxPriority() {
        //Given
        var returnList = List.of(PriceEntity.builder()
                        .price(new BigDecimal(1))
                        .priceList(1)
                        .brandId(1)
                        .productId(1)
                        .priority(1)
                        .startDate((long) 1)
                        .endDate((long) 3)
                        .currency("EUR")
                        .build(),
                PriceEntity.builder()
                        .price(new BigDecimal(2))
                        .priceList(2)
                        .brandId(1)
                        .productId(1)
                        .priority(2)
                        .startDate((long) 1)
                        .endDate((long) 4)
                        .currency("EUR")
                        .build());

        var priceCriteriaDto = new PriceCriteriaDto()
                .productId(1)
                .brandId(1)
                .applyDate("2001-01-01 12:00:00");

        //When
        when(ecommercePriceRepository.findPriceByProductAndBrand(any())).thenReturn(returnList);
        var price = ecommercePriceService.getPrice(priceCriteriaDto);

        //Then
        Assertions.assertThat(price.getPrice()).isEqualTo(returnList.get(1).getPrice());
        Assertions.assertThat(price.getPriceList()).isEqualTo(returnList.get(1).getPriceList());
    }

}