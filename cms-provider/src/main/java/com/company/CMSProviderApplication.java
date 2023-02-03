package com.company;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class CMSProviderApplication {
    public static void main(String[] args){
        SpringApplication.run(CMSProviderApplication.class, args);
    }
}
