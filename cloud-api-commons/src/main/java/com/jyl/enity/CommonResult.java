package com.jyl.enity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 给前端传回用的类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonResult<T> {
    private Integer code;
    private String message;
    private T data;

    //上面注解是全参和空参构造方法，有可能数据为空，这里在写这种情况的构造方法
    public CommonResult(Integer code, String message) {
        this(code, message, null);
    }
}