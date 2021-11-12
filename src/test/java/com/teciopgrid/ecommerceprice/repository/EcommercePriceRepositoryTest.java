package com.teciopgrid.ecommerceprice.repository;

import com.teciopgrid.ecommerceprice.repository.entity.PriceEntity;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.stream.IntStream;


@ExtendWith(SpringExtension.class)
@SpringBootTest
@PropertySource("classpath:application.yml")
class EcommercePriceRepositoryTest {

    @Autowired
    EcommercePriceRepository ecommercePriceRepository;

    @AfterEach
    void deleteAll() {
        ecommercePriceRepository.deleteAll();
    }

    @Test
    void shouldReturnConcretePriceEntity() {
        //Given
        IntStream.range(1, 3)
                .mapToObj(this::buildEntity)
                .forEach(entity -> ecommercePriceRepository.save(entity));

        var priceEntity = buildEntity(1);
        //When
        var result = ecommercePriceRepository.findPriceByProductAndBrand(priceEntity);
        //Then
        Assertions.assertThat(result.size()).isEqualTo(1);
        Assertions.assertThat(result.get(0)).isEqualToIgnoringGivenFields(buildEntity(1), "applyDate");
    }

    @Test
    void shouldReturnPriceEntityIntoRange() {
        //Given
        var firstPriceEntity = PriceEntity.builder()
                .price(new BigDecimal(1))
                .priceList(1)
                .brandId(1)
                .productId(1)
                .priority(1)
                .startDate((long) 1)
                .endDate((long) 3)
                .currency("EUR")
                .build();

        ecommercePriceRepository.save(firstPriceEntity);

        var twoPriceEntity = PriceEntity.builder()
                .price(new BigDecimal(1))
                .priceList(1)
                .brandId(1)
                .productId(1)
                .priority(1)
                .startDate((long) 1)
                .endDate((long) 4)
                .currency("EUR")
                .build();

        ecommercePriceRepository.save(twoPriceEntity);

        var priceEntity = PriceEntity.builder()
                .brandId(1)
                .productId(1)
                .applyDate(3)
                .build();
        //When
        var result = ecommercePriceRepository.findPriceByProductAndBrand(priceEntity);
        //Then
        Assertions.assertThat(result.size()).isEqualTo(2);
        Assertions.assertThat(result.get(0)).isEqualToIgnoringGivenFields(firstPriceEntity, "applyDate");
        Assertions.assertThat(result.get(1)).isEqualToIgnoringGivenFields(twoPriceEntity, "applyDate");

    }

    private PriceEntity buildEntity(final int position) {
        return PriceEntity.builder()
                .price(new BigDecimal(position))
                .priceList(position)
                .brandId(position)
                .productId(position)
                .priority(position)
                .startDate((long) position)
                .endDate((long) position + 2)
                .currency("EUR")
                .applyDate(position)
                .build();
    }

}