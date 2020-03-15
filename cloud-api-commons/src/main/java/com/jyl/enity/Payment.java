package com.jyl.enity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor  //全参构造方法
@NoArgsConstructor  //空参构造方法
public class Payment {

    private Long id;
    private String serial;
}
