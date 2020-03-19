package com.jyl.controller;

import com.jyl.service.PaymentHystrixService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;

@RestController
@Slf4j
@DefaultProperties(defaultFallback = "payment_Global_FallbackMethod")       //配置全局服务降级，如果有详细配置单独方法就降级到单独方法，否则就到全局方法
public class OrderHystrixController {

    @Resource
    private PaymentHystrixService paymentHystrixService;

    @GetMapping("/consumer/payment/hystrix/ok/{id}")
    public String paymentInfo_OK(@PathVariable("id") Integer id){
        String result = paymentHystrixService.paymentInfo_OK(id);
        return result;
    }


    /**
     * 客户端服务降级，启动类开启注解，然后yml也开启
     * 演示全局配置方法和客户端降级方法(优先服务端 服务端5秒，客户端设置1.5秒，1.5秒直接就那个)
     */
//    @HystrixCommand(fallbackMethod = "PaymentTimeOutFallbackMethod",commandProperties = {
//            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "1500")
//    })
    @GetMapping("/consumer/payment/hystrix/timeout/{id}")
    @HystrixCommand  //演示全局配置方法
    public String paymentInfo_TimeOut(@PathVariable("id") Integer id){
//        int age = 10/0;
        String result = paymentHystrixService.paymentInfo_TimeOut(id);
        return result;
    }

    // 消费者局部服务降级
    public String PaymentTimeOutFallbackMethod(@PathVariable("id") Integer id) {
        return "这是消费者局部服务降级返回方法，我是消费者80，对方支付系统繁忙请10秒钟后再试或者自己运行出错请检查自己，o(╥﹏╥)o";
    }

    //下面是全局fallback方法
    public String payment_Global_FallbackMethod(){
        return "这是全局服务降级返回方法，Global异常处理信息，请稍后再试，o(╥﹏╥)o";
    }
}
