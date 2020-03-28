package com.jyl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * sentinel,ribbon环境-消费者84
 * SentinelResource这个注解，fallback管运行异常，SentinelResource注解管配置违规
 */
@EnableDiscoveryClient
@SpringBootApplication
@EnableFeignClients     //激活feign
public class OrderNacosMain84 {
    public static void main(String[] args) {
        SpringApplication.run(OrderNacosMain84.class, args);
    }
}
