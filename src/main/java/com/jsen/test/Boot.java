package com.jsen.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication(exclude = {
        DataSourceAutoConfiguration.class
})
@EnableSwagger2
// @EnableTransactionManagement
public class Boot {
    public static void main(String[] args) {
        SpringApplication.run(Boot.class, args);
    }
}

