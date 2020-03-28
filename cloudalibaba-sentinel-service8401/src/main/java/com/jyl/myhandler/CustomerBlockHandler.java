package com.jyl.myhandler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.jyl.enity.CommonResult;
import com.jyl.enity.Payment;


/**
 * 自定义限流处理逻辑
 * 就是全局的兜底方法
 */
public class CustomerBlockHandler {

    public static CommonResult handlerException(BlockException exception) {
        return new CommonResult(4444, "按客户自定义,global handlerException", new Payment(2020L, "serial0003"));
    }
    public static CommonResult handlerException2(BlockException exception) {
        return new CommonResult(4444, "按客户自定义2,global handlerException", new Payment(2020L, "serial0003"));
    }
}
