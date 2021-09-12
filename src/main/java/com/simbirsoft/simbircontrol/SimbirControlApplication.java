package com.simbirsoft.simbircontrol;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

import java.util.Locale;

@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient
public class SimbirControlApplication {
    public static void main(String[] args) {
        SpringApplication.run(SimbirControlApplication.class, args);
    }
}