package com.teciopgrid.ecommerceprice.controller;

import com.teciopgrid.ecommerceprice.controller.dto.PriceResultDto;
import com.teciopgrid.ecommerceprice.exception.NotFoundEcommerceEntityException;
import com.teciopgrid.ecommerceprice.exception.NotFoundEcommercePriceCriteriaDtoException;
import com.teciopgrid.ecommerceprice.service.EcommercePriceService;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;

import static java.nio.charset.Charset.defaultCharset;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.util.StreamUtils.copyToString;

@ExtendWith(MockitoExtension.class)
class EcommercePriceControllerImplTest {

    @Mock
    EcommercePriceService ecommercePriceService;

    MockMvc mockMvc;

    @BeforeEach
    void startUp() {
        mockMvc = MockMvcBuilders
                .standaloneSetup(new EcommercePriceControllerImpl(ecommercePriceService))
                .setControllerAdvice(new GlobalErrorHandler())
                .build();
    }

    @Test
    @SneakyThrows
    void shouldReturnEcommercePriceResult() {
        //Given
        var inputJson = EcommercePriceControllerImplTest.buildJson();

        var priceResultDto = new PriceResultDto()
                .price(new BigDecimal("1.1"))
                .priceList(1)
                .applyDateStart("2021-1-1 12:00:00")
                .applyDateEnd("2021-1-1 12:00:00")
                .productId(1)
                .brandId(1);

        //When
        when(ecommercePriceService.getPrice(any())).thenReturn(priceResultDto);

        //Then
        mockMvc.perform(post("/ecommerce/price/")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .content(inputJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("price").value("1.1"))
                .andExpect(jsonPath("priceList").value("1"))
                .andExpect(jsonPath("applyDateStart").value("2021-1-1 12:00:00"))
                .andExpect(jsonPath("applyDateEnd").value("2021-1-1 12:00:00"))
                .andExpect(jsonPath("productId").value("1"))
                .andExpect(jsonPath("brandId").value("1"));

    }

    @Test
    @SneakyThrows
    void shouldReturnControllerRuntimeException() {
        //Given
        var inputJson = EcommercePriceControllerImplTest.buildJson();

        //When
        when(ecommercePriceService.getPrice(any())).thenThrow(new RuntimeException("Error test"));

        //Then
        mockMvc.perform(post("/ecommerce/price/")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .content(inputJson))
                .andExpect(status().is5xxServerError())
                .andExpect(jsonPath("method").value("POST"))
                .andExpect(jsonPath("uri").value("/ecommerce/price/"))
                .andExpect(jsonPath("message").value("Error test"))
                .andExpect(jsonPath("status-code").value("500 INTERNAL_SERVER_ERROR"));

    }

    @Test
    @SneakyThrows
    void shouldReturnControllerNotFoundEcommerceEntityException() {
        //Given
        var inputJson = EcommercePriceControllerImplTest.buildJson();

        //When
        when(ecommercePriceService.getPrice(any())).thenThrow(new NotFoundEcommerceEntityException());

        //Then
        mockMvc.perform(post("/ecommerce/price/")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .content(inputJson))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("method").value("POST"))
                .andExpect(jsonPath("uri").value("/ecommerce/price/"))
                .andExpect(jsonPath("message").value("There arent result for the request"))
                .andExpect(jsonPath("status-code").value("204 NO_CONTENT"));

    }

    @Test
    @SneakyThrows
    void shouldReturnControllerNotFoundEcommercePriceCriteriaDtoException() {
        //Given
        var inputJson = EcommercePriceControllerImplTest.buildJson();

        //When
        when(ecommercePriceService.getPrice(any())).thenThrow(new NotFoundEcommercePriceCriteriaDtoException());

        //Then
        mockMvc.perform(post("/ecommerce/price/")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .content(inputJson))
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("method").value("POST"))
                .andExpect(jsonPath("uri").value("/ecommerce/price/"))
                .andExpect(jsonPath("message").value("PriceCriteriaDto was null or empty during the process"))
                .andExpect(jsonPath("status-code").value("400 BAD_REQUEST"));

    }


    @SneakyThrows
    private static String buildJson() {
        return new String(copyToString(new ClassPathResource("input.json")
                .getInputStream(), defaultCharset()).getBytes());
    }

}