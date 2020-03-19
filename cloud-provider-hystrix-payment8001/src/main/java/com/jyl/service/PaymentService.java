package com.jyl.service;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.concurrent.TimeUnit;

@Service
public class PaymentService {
    /**
     * 正常访问，肯定ok
     * @param id
     * @return
     */
    public String paymentInfo_OK(Integer id) {
        return "线程池： " + Thread.currentThread().getName() + " PaymenyInfo_OK,id: " + id + "\t" + "O(∩_∩)O哈哈~";
    }

    /**
     * 服务降级演示，出错或者超时，超时上面正常设置为3秒，下面等待设置5秒就可以演示超时导致服务降级
     * @param id
     * @return
     */
    @HystrixCommand(fallbackMethod = "paymentInfo_TimeOutHandler",commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "5000")
    })//fallbackMethod指定超时，出错等使服务降级指定执行的方法，HystrixProperty设置超时时间为3秒钟为上限
    public String paymentInfo_TimeOut(Integer id) {

        int timeNumber = 3000;
//         int timeNumber = 10/0;
        try {
            TimeUnit.MILLISECONDS.sleep(timeNumber);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "线程池： " + Thread.currentThread().getName() + " PaymenyInfo_TimeOut,id: " + id + "\t" + "O(∩_∩)O哈哈~" + " 耗时" + timeNumber + "毫秒";
    }

    /**
     * 服务降级最后处理的方法
     * @param id
     * @return
     */
    public String paymentInfo_TimeOutHandler(Integer id) {
        return "线程池： " + Thread.currentThread().getName() + "这是提供者局部服务降级返回方法 8001系统繁忙系统报错,请稍后再试id: " + id + "\t" + "( Ĭ ^ Ĭ )";
    }

    // 服务熔断，设置时间窗口期中请求失败次数达到百分之60，就会服务熔断，访问正数也会显示失败，等正确率慢慢回升，才解除熔断模式
    @HystrixCommand(fallbackMethod = "paymentCircuitBreaker_fallback",commandProperties ={
            @HystrixProperty(name = "circuitBreaker.enabled",value = "true"), //是否开启断路器
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "10"), //请求次数
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "10000"), //时间窗口期
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "60"),//失败率达到多少后跳闸
    })
    public String paymentCircuitBreaker(@PathVariable("id") Integer id) {
        if (id < 0) {
            throw new RuntimeException("******id 不能为负数");
        }
        String serialNumber = IdUtil.simpleUUID(); //hutool工具类
        return Thread.currentThread().getName()+"\t"+ "调用成功，流水号：" + serialNumber;
    }

    public String paymentCircuitBreaker_fallback(@PathVariable("id") Integer id) {
        return "id 不能负数，请稍后再试，o(╥﹏╥)o id：" + id;
    }


}
