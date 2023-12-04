package xyz.anomatver.soa3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class GrammyApplication {

    public static void main(String[] args) {
        SpringApplication.run(GrammyApplication.class, args);
    }

}
