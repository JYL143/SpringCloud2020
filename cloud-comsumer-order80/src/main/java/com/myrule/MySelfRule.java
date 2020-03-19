package com.myrule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * 必须放到启动类所在包的外面那个包
 * 启动类还要指定使用这个类
 */
@Configuration
public class MySelfRule
{
	@Bean
	public IRule myRule()
	{
		return new RandomRule();// Ribbon默认是轮询，我自定义为随机

//		return new RandomRule_ZY();// 我自定义为每台机器5次
	}
}
