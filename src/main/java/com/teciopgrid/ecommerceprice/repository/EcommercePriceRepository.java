package com.teciopgrid.ecommerceprice.repository;

import com.teciopgrid.ecommerceprice.repository.entity.PriceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * The interface Ecommerce price repository.
 */
public interface EcommercePriceRepository extends JpaRepository<PriceEntity, PriceEntity> {

    /**
     * Find price by product and brand list.
     *
     * @param price the price
     * @return the list
     */
    @Query("SELECT DISTINCT PRICE "
            + "FROM PriceEntity PRICE "
            + "WHERE PRICE.brandId = :#{#price.brandId} and PRICE.productId = :#{#price.productId} "
            + "and :#{#price.applyDate}>= PRICE.startDate and :#{#price.applyDate} <= PRICE.endDate")
    List<PriceEntity> findPriceByProductAndBrand(@Param("price") final PriceEntity price);
}
