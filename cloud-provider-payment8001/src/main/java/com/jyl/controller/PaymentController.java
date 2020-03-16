package com.jyl.controller;


import com.jyl.enity.CommonResult;
import com.jyl.enity.Payment;
import com.jyl.service.PaymentService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;


@RestController
@Slf4j
public class PaymentController {

    @Resource
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @Resource
    private DiscoveryClient discoveryClient; //暴露服务 spring包

    @PostMapping(value = "/payment/create")
    public CommonResult create( @RequestBody Payment payment) {
        int result = paymentService.create(payment);
        log.info("插入结果:" + result);
        if (result > 0) {
            return new CommonResult(200, "插入成功,serverPort: " + serverPort, result);
        } else {
            return new CommonResult(444, "插入失败", null);
        }
    }

    @GetMapping(value = "/payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id) {
        Payment paymentById = paymentService.getPaymentById(id);
        log.info("查询结果:" + paymentById);
        if (paymentById != null) {
            return new CommonResult(200, "查询成功,serverPort: "+ serverPort, paymentById);
        } else {
            return new CommonResult(444, "没有对应记录，查询id:" + id, null);
        }
    }


    //测试暴露服务，这里就自测，消费者可以像正常一样的远程访问这个方法就可以获取到这个微服务的信息
    @GetMapping(value = "/payment/discovery")
    public Object discovery(){
        List<String> services = discoveryClient.getServices();  //获取注册中心所有的微服务
        for (String element : services) {
            log.info("****element: " +element);
        }
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE"); //获取具体的微服务，进一步获取微服务信息
        for (ServiceInstance instance:instances) {
            log.info(instance.getServiceId()+"\t"+instance.getHost()+"\t"+instance.getPort()+"\t"+instance.getUri());
        }
        return this.discoveryClient;
    }
}
