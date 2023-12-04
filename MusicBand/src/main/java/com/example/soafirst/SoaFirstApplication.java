package com.example.soafirst;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class SoaFirstApplication {

    public static void main(String[] args) {
        SpringApplication.run(SoaFirstApplication.class, args);
    }

}
