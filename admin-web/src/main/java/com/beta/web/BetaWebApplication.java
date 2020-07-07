package com.beta.web;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication()
@MapperScan("com.mapper")
@ComponentScan(basePackages = {"com.beta","com.stock", "com.mapper","com.interceptor","com.beta.web.exceptionHandler"})

public class BetaWebApplication {
    public static void main(String[] args) {
        SpringApplication.run(BetaWebApplication.class, args);
    }

}
