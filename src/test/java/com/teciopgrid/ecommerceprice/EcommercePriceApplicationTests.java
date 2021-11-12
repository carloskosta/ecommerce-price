package com.teciopgrid.ecommerceprice;

import com.teciopgrid.ecommerceprice.controller.EcommercePriceControllerImpl;
import com.teciopgrid.ecommerceprice.controller.GlobalErrorHandler;
import com.teciopgrid.ecommerceprice.service.EcommercePriceService;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static java.nio.charset.Charset.defaultCharset;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.util.StreamUtils.copyToString;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class EcommercePriceApplicationTests {

    @Autowired
    EcommercePriceService ecommercePriceService;

    MockMvc mockMvc;

    @BeforeEach
    void startUp() {
        mockMvc = MockMvcBuilders
                .standaloneSetup(new EcommercePriceControllerImpl(ecommercePriceService))
                .setControllerAdvice(new GlobalErrorHandler())
                .build();
    }

    @SneakyThrows
    @Test
    void integrationTestOne() {
        //Given
        var json = buildJson("test-one.json");

        //When-Then
        mockMvc.perform(post("/ecommerce/price/")
                        .contentType(org.springframework.http.MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("price").value("35.5"))
                .andExpect(jsonPath("priceList").value("1"))
                .andExpect(jsonPath("applyDateStart").value("2020-06-14T00:00"))
                .andExpect(jsonPath("applyDateEnd").value("2020-12-31T23:59:59"))
                .andExpect(jsonPath("productId").value("35455"))
                .andExpect(jsonPath("brandId").value("1"));
    }


    @SneakyThrows
    @Test
    void integrationTestTwo() {
        //Given
        var json = buildJson("test-two.json");

        //When-Then
        mockMvc.perform(post("/ecommerce/price/")
                        .contentType(org.springframework.http.MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("price").value("25.45"))
                .andExpect(jsonPath("priceList").value("2"))
                .andExpect(jsonPath("applyDateStart").value("2020-06-14T15:00"))
                .andExpect(jsonPath("applyDateEnd").value("2020-06-14T18:30"))
                .andExpect(jsonPath("productId").value("35455"))
                .andExpect(jsonPath("brandId").value("1"));

    }

    @SneakyThrows
    @Test
    void integrationTestThree() {
        //Given
        var json = buildJson("test-three.json");

        //When-Then
        mockMvc.perform(post("/ecommerce/price/")
                        .contentType(org.springframework.http.MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("price").value("35.5"))
                .andExpect(jsonPath("priceList").value("1"))
                .andExpect(jsonPath("applyDateStart").value("2020-06-14T00:00"))
                .andExpect(jsonPath("applyDateEnd").value("2020-12-31T23:59:59"))
                .andExpect(jsonPath("productId").value("35455"))
                .andExpect(jsonPath("brandId").value("1"));
    }

    @SneakyThrows
    @Test
    void integrationTestFour() {
        //Given
        var json = buildJson("test-four.json");

        //When-Then
        mockMvc.perform(post("/ecommerce/price/")
                        .contentType(org.springframework.http.MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("price").value("30.5"))
                .andExpect(jsonPath("priceList").value("3"))
                .andExpect(jsonPath("applyDateStart").value("2020-06-15T00:00"))
                .andExpect(jsonPath("applyDateEnd").value("2020-06-15T11:00"))
                .andExpect(jsonPath("productId").value("35455"))
                .andExpect(jsonPath("brandId").value("1"));
    }

    @SneakyThrows
    @Test
    void integrationTestFive() {
        //Given
        var json = buildJson("test-five.json");

        //When-Then
        mockMvc.perform(post("/ecommerce/price/")
                        .contentType(org.springframework.http.MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("price").value("38.95"))
                .andExpect(jsonPath("priceList").value("4"))
                .andExpect(jsonPath("applyDateStart").value("2020-06-15T16:00"))
                .andExpect(jsonPath("applyDateEnd").value("2020-12-31T23:59:59"))
                .andExpect(jsonPath("productId").value("35455"))
                .andExpect(jsonPath("brandId").value("1"));
    }


    @SneakyThrows
    private static String buildJson(final String jsonEntry) {
        return new String(copyToString(new ClassPathResource(jsonEntry)
                .getInputStream(), defaultCharset()).getBytes());
    }


}
