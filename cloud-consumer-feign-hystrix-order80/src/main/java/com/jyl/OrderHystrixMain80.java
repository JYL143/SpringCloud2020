package com.jyl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;


/**
 * 断路器hystrix消费者model
 * 服务降级，全局配置，单独配置，接口配置
 */
@SpringBootApplication
@EnableFeignClients     //开启openfeign
@EnableHystrix          //开启客户端hystrix
public class OrderHystrixMain80 {
    public static void main(String[] args) {
        SpringApplication.run(OrderHystrixMain80.class, args);
    }
}
