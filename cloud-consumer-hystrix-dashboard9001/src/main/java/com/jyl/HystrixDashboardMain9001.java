package com.jyl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;


/**
 * hystrix豪猪监控页面，被监控者必须有监控那个依赖，新版的还需要在被监控类的主启动类加代码
 * 访问localhost:9001/hystrix有类似于eureka的页面
 *然后监控页面输入http://localhost:8001/hystrix.stream   被监控的路径加固定后面的，还有设置几秒监控一次
 */
@SpringBootApplication
@EnableHystrixDashboard //开启hystrix豪猪监控页
public class HystrixDashboardMain9001 {
    public static void main(String[] args) {
        SpringApplication.run(HystrixDashboardMain9001.class, args);
    }
}
