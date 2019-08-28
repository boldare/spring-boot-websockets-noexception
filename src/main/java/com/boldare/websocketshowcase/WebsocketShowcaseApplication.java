package com.boldare.websocketshowcase;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.integration.annotation.IntegrationComponentScan;

@SpringBootApplication
@IntegrationComponentScan("com.boldare.websocketshowcase.integration")
public class WebsocketShowcaseApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebsocketShowcaseApplication.class, args);
    }

}
