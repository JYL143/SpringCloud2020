package com.jyl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 *提供者集群8002
 */
@EnableDiscoveryClient//注册服务于服务发现 适用于多种注册中心 eureka zookeeper  consul
@EnableEurekaClient //注册服务于服务发现 适用于eureka一种注册中心
@SpringBootApplication
public class PaymentMain8002 {
    public static void main(String[] args) {
        SpringApplication.run(PaymentMain8002.class,args);
    }
}
