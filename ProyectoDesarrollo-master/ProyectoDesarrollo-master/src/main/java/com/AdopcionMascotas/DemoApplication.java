package com.AdopcionMascotas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

//    @Bean
//    public org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder BCryptPasswordEncoder() {
//        return new org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder();
//    }
 
}
