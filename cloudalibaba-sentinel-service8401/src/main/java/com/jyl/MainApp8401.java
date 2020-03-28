package com.jyl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * alibaba-sentinel技术测试模块
 *流量监控(qbs,流控效果：直接失败，预热，排队等待)
 * 服务降级  降级策略：RT(平均响应时间)，异常比例，异常数
 * 热点限流:热点参数列外项
 * 系统规则，其中一种规则就是类似qbs，不管你访问哪个方法都执行规则
 * ,@SentinelResource注解是当你不符合热点规则或者出异常的时候，就可以执行指定方法，也可以单独写一个类，然后指定该类下的哪个方法
 */
@SpringBootApplication
@EnableDiscoveryClient
public class MainApp8401 {
    public static void main(String[] args) {
        SpringApplication.run(MainApp8401.class, args);
    }
}
