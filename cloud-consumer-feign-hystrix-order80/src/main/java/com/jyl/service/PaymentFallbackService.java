package com.jyl.service;

import org.springframework.stereotype.Component;

/**
 *这个还要继承原service
 */
@Component
public class PaymentFallbackService implements  PaymentHystrixService{
    @Override
    public String paymentInfo_OK(Integer id) {
        return "-------这是模拟消费者ok方法，然后把提供者微服务停止，在访问的情况。" +
                "这是利用feign的接口+注解机制，在接口上定义该接口的全部方法都加上统一的处理方法 fall back-paymentInfo_OK,o(╥﹏╥)o";
    }

    @Override
    public String paymentInfo_TimeOut(Integer id) {
        return "这是利用feign的接口+注解机制，在接口上定义该接口的全部方法都加上统一的处理方法-------PaymentFallbackService fall back-paymentInfo_TimeOut,o(╥﹏╥)o";
    }
}
