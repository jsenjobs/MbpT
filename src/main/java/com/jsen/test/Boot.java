package com.jsen.test;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication(exclude = {
        DataSourceAutoConfiguration.class
})
@MapperScan("com.jsen.test.mapper")
@EnableSwagger2
@EnableDiscoveryClient
@EnableEurekaClient
// @EnableTransactionManagement
public class Boot {
    public static void main(String[] args) {
        SpringApplication.run(Boot.class, args);
    }
}

