package com.actstrady.wmall.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.actstrady.wmall")
@EnableJpaRepositories(basePackages = "com.actstrady.wmall")
@EntityScan(basePackages = "com.actstrady.wmall")
public class WmallApplication {

    public static void main(String[] args) {
        SpringApplication.run(WmallApplication.class, args);
    }
}