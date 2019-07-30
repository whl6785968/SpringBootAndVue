package com.sandalen.jwts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class JwtsApplication {

    public static void main(String[] args) {
        SpringApplication.run(JwtsApplication.class, args);
    }

}
