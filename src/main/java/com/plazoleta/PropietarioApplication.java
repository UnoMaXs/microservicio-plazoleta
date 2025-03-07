package com.plazoleta;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class PropietarioApplication {

    public static void main(String[] args) {
        SpringApplication.run(PropietarioApplication.class, args);
    }

}
