package xyz.anomatver.soa3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.transport.http.MessageDispatcherServlet;

@SpringBootApplication
@EnableDiscoveryClient
@EnableWs
public class GrammyApplication {

    public static void main(String[] args) {
        SpringApplication.run(GrammyApplication.class, args);
    }
}
