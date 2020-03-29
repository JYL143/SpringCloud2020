package com.jyl.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.jyl.dao")
public class MyBatisConfig {
}
