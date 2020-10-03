package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages={"com.model"})
public class SpringLinuxMysqlApp {
    public static void main(String[] args) {
        SpringApplication.run(SpringLinuxMysqlApp.class,args);
    }

}
