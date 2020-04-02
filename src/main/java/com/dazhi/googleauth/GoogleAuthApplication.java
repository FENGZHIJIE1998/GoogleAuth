package com.dazhi.googleauth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class GoogleAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(GoogleAuthApplication.class, args);
    }

}
