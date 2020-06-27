package com.wenting.springcloud.config;


import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignLogConfig {
    @Bean
    public Logger.Level feignLoggerLevel(){
        return Logger.Level.FULL;  // 开启详细日志
    }
}
