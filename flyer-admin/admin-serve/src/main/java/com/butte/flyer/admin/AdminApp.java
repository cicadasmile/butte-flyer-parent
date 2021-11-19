package com.butte.flyer.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.butte"})
public class AdminApp {
    public static void main(String[] args) {
        SpringApplication.run(AdminApp.class,args) ;
    }
}