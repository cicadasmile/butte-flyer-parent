package com.butte.flyer.facade;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.butte"})
@EnableFeignClients(basePackages={"com.butte"})
public class FacadeApp {
    public static void main(String[] args) {
        SpringApplication.run(FacadeApp.class,args) ;
    }
}