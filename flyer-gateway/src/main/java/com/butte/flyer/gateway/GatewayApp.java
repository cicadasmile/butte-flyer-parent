package com.butte.flyer.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.butte"})
public class GatewayApp {
    public static void main(String[] args) {
        SpringApplication.run(GatewayApp.class,args) ;
    }
}