package com.jyl.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;

import com.jyl.enity.CommonResult;
import com.jyl.enity.Payment;
import com.jyl.myhandler.CustomerBlockHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * SentinelResource注解 单独写一个兜底方法的类，然后注解 指定哪个类的哪个方法
 */
@RestController
public class RateLimitController {

    @GetMapping("/byResource")
    @SentinelResource(value = "byResource",blockHandler = "handleException")
    public CommonResult byResource(){
        return new CommonResult(200, "按资源名称限流测试ok", new Payment(2020L, "serial001"));
    }

    public CommonResult handleException(BlockException exception) {
        return new CommonResult(444, exception.getClass().getCanonicalName() + "\t 服务不可用");
    }

    //测试持久化的sentinel，写到nacos上用json格式
    @GetMapping("/rateLimit/byUrl")
    @SentinelResource(value = "byUrl",blockHandlerClass = CustomerBlockHandler.class, blockHandler = "handlerException")
    public CommonResult byUrl(){
        return new CommonResult(200,"按url限流测试OK", new Payment(2020L, "serial001"));
    }

    //CustomerBlockHandler
    @GetMapping("/rateLimit/CustomerBlockHandler")
    @SentinelResource(value = "CustomerBlockHandler",blockHandlerClass = CustomerBlockHandler.class, blockHandler = "handlerException2")
    public CommonResult CustomerBlockHandler(){
        return new CommonResult(200,"按客户自定义限流测试OK", new Payment(2020L, "serial003"));
    }

}
