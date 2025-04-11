package com.example.syncdatamarket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class SyncDataMarketApplication {

    public static void main(String[] args) {
        SpringApplication.run(SyncDataMarketApplication.class, args);
    }

}
