package com.jyl;
import com.myrule.MySelfRule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

/**
 * 消费者，负载均衡ribbon
 */

@RibbonClient(name="CLOUD-PAYMENT-SERVICE",configuration= MySelfRule.class) //这个自定义配置类不能放在启动类扫描的包下
@EnableEurekaClient //注册服务
@SpringBootApplication
public class OrderMain80 {
    public static void main(String[] args) {
        SpringApplication.run(OrderMain80.class, args);
    }
}
