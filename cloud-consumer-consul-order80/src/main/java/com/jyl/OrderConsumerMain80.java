package com.jyl;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


/**
 * consul作为注册中心model-消费者80
 */
@SpringBootApplication
@EnableDiscoveryClient
public class OrderConsumerMain80 {
    public static void main(String[] args) {
        SpringApplication.run(OrderConsumerMain80.class, args);
    }
}
