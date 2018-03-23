package com.jsen.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class Boot {
    public static void main(String[] args) {
        SpringApplication.run(Boot.class, args);
    }
}

