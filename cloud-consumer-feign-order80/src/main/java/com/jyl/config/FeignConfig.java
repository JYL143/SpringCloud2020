package com.jyl.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfig {

    //openfeign日志打印功能，还需要去yml配置
    @Bean
    Logger.Level feignLoggerLevel(){
        return Logger.Level.FULL;   //full详细
    }

}
