package com.step.salehome;

import org.apache.logging.log4j.core.lookup.MainMapLookup;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SaleHomeApplication {
    public static void main(String[] args) {
        MainMapLookup.setMainArguments();
        SpringApplication.run(SaleHomeApplication.class, args);
    }
}
