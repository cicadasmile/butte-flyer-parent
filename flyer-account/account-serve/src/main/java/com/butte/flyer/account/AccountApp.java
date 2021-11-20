package com.butte.flyer.account;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * 用户服务启动类
 * @author 公众号:知了一笑
 * @since 2021-09-05 15:17
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.butte"})
@EnableFeignClients(basePackages={"com.butte"})
public class AccountApp {
    public static void main(String[] args) {
        SpringApplication.run(AccountApp.class,args) ;
    }
}
