package com.butte.flyer.quartz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.butte"})
@EnableFeignClients(basePackages={"com.butte"})
public class QuartzApp {
    public static void main(String[] args) {
        SpringApplication.run(QuartzApp.class,args) ;
    }
}