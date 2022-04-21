package com.lzj;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class EurekaServerApplication_rep1 {

    public static void main(String[] args) {
        SpringApplication.run(EurekaServerApplication_rep1.class, args);
    }

}