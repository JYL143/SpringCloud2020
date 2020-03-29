package com.jyl.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    private Long id;
    private Long userId;        //用户id
    private long productId;     //产品id
    private Integer count;      //数量
    private BigDecimal money;   //金额
    private Integer status;     //状态 0创建中，1已完结

}
