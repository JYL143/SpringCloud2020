package com.jyl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 *openfeign负载均衡model
 * 多写一个接口 然后接口上注解指定提供者微服务名，然后接口里的方法上指定调用提供者的方法，相当于这个接口代替了restTemplate模板
 */
@SpringBootApplication
@EnableFeignClients     //激活feign负载均衡
public class OrderFeignMain80 {
    public static void main(String[] args) {
        SpringApplication.run(OrderFeignMain80.class, args);
    }
}
