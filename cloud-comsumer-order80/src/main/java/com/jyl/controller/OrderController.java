package com.jyl.controller;


import com.jyl.enity.CommonResult;
import com.jyl.enity.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;



@RestController
@Slf4j
public class OrderController {


//       public static final String PAYMENT_URL = "http://localhost:8001";  //单价版
  public static final String PAYMENT_URL = "http://CLOUD-PAYMENT-SERVICE";  //集群，同一个服务名，多个端口，负载均衡调用

    @Resource
    private RestTemplate restTemplate;

    @GetMapping("/consumer/payment/create")
    public CommonResult<Payment> create(Payment payment) {

        return restTemplate.postForObject(PAYMENT_URL + "/payment/create", payment, CommonResult.class);
    }

    @GetMapping("/consumer/payment/get/{id}")
    public CommonResult<Payment> getPayment(@PathVariable("id") Long id) {
        return restTemplate.getForObject(PAYMENT_URL + "/payment/get/" + id, CommonResult.class);
    }

    @GetMapping("/consumer/payment/getForEntity/{id}")
    public CommonResult<Payment> getPayment2(@PathVariable("id") Long id) {

        ResponseEntity<CommonResult> entity = restTemplate.getForEntity(PAYMENT_URL + "/payment/get/" + id, CommonResult.class);

        if (entity.getStatusCode().is2xxSuccessful()) { //判断如果状态码是2xx
            log.info(entity.getStatusCode() + "\t" + entity.getHeaders());
            return entity.getBody();  //返回请求体
        } else {
            return new CommonResult<>(444, "操作失败");
        }
    }
}
