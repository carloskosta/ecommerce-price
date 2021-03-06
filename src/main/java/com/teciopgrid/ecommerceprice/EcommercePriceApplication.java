package com.teciopgrid.ecommerceprice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class EcommercePriceApplication {

    public static void main(final String[] args) {
        SpringApplication.run(EcommercePriceApplication.class, args);
    }

}
