package com.casic.oarp.datavisual;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.casic.oarp.datavisual")
@MapperScan(basePackages = "com.casic.orap.datavisual.mapper")
public class DatavisualApplication {

    public static void main(String[] args) {
        SpringApplication.run(DatavisualApplication.class, args);
    }
}
