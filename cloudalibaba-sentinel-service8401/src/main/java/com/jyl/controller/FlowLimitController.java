package com.jyl.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.sun.deploy.security.BlockedException;
import com.sun.org.apache.regexp.internal.RE;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
public class FlowLimitController {

    //流量控制测试
    @GetMapping("/testA")
    public String testA(){
//        try {
//            TimeUnit.MILLISECONDS.sleep(800);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        return "---------testA";
    }

    //流量控制测试
    @GetMapping("/testB")
    public String testB(){
//        log.info("测试流控效果为：排队等待");
        return "----------testB";
    }

    //服务降级的RT降级策略
    @GetMapping("/testD")
    public String testD(){
//        try {
//            TimeUnit.SECONDS.sleep(1);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        log.info("testD 测试RT,这个要用线程工具测试");
        log.info("testD 异常比例,这个要用线程工具测试");
        int age = 10/0;
        return "------testD";
    }


    //测试异常数
    @GetMapping("/testE")
    public String testE(){
        log.info("testD 测试异常数");
        int age = 10/0;
        return "------testD 测试异常数";
    }

    //测试热点限流
    @GetMapping("/testHotKey")
    @SentinelResource(value = "testHotKey",blockHandler = "deal_testHotKey")  //指定兜底方法和hystrix的fallback差不多，第一个value给热点规则的资源名使用
    public String testHotKey(@RequestParam(value = "p1",required = false) String p1,
                             @RequestParam(value = "p2", required = false) String p2) {
        return "-------testHotKey，测试热点限流";
    }

    //上面新注解的兜底方法，违背热点规则就执行这个方法
    public String deal_testHotKey(String p1, String p2, BlockException exception){
        return "----deal_testHotKey,o(╥﹏╥)o，这是兜底方法";

    }

}
