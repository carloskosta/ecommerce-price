package com.teciopgrid.ecommerceprice.controller;

import com.teciopgrid.ecommerceprice.controller.dto.PriceCriteriaDto;
import com.teciopgrid.ecommerceprice.controller.dto.PriceResultDto;
import com.teciopgrid.ecommerceprice.controller.service.EcommerceApi;
import com.teciopgrid.ecommerceprice.service.EcommercePriceService;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class EcommercePriceControllerImpl implements EcommerceApi {

    private final EcommercePriceService ecommercePriceService;

    public ResponseEntity<PriceResultDto> getPrice(final @ApiParam(value = "PriceCriteria")
                                                   @Valid @RequestBody(required = false)
                                                           PriceCriteriaDto priceCriteriaDto) {
        return ResponseEntity.ok().body(ecommercePriceService.getPrice(priceCriteriaDto));
    }

}
