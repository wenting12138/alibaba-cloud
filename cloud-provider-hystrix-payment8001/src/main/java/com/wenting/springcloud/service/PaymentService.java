package com.wenting.springcloud.service;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.cloud.commons.util.IdUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class PaymentService {

    //  ============ 服务降级
    public String paymentInfo(Integer id){
        return "线程池: " + Thread.currentThread().getName() + "paymentInfo .." + id + "O(∩_∩)O";
    }

    @HystrixCommand(fallbackMethod = "paymentTimeoutHandler",commandProperties = {
            // 超时时间 3秒钟
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")
    })
    public String paymentTimeout(Integer id){
        try {
//            int a = 1 / 0;
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "线程池: " + Thread.currentThread().getName() + "paymentTimeout .." + id + "O(∩_∩)O" + "耗时三秒钟";
    }

    public String paymentTimeoutHandler(Integer id){
        return Thread.currentThread().getName() + "对不起,请稍后再试/(ㄒoㄒ)/~~" + id;
    }


    //  ============服务熔断
    @HystrixCommand(fallbackMethod = "paymentCircuitBreaker_fallback", commandProperties = {
            // 是否开启短路器
            @HystrixProperty(name = "circuitBreaker.enabled", value = "true"),
            // 请求次数
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
            // 时间窗口期
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"),
            // 失败率达到多少后熔断
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60"),
    })
    public String paymentCircuitBreaker(@PathVariable("id") Integer id) {

        if (id < 0) {
            throw new RuntimeException("****** id 不能为负数");
        }
        String serialNumber = UUID.randomUUID().toString();
        return Thread.currentThread().getName() + "调用成功,流水号" + serialNumber;
    }


    public String paymentCircuitBreaker_fallback(@PathVariable("id") Integer id){
        return "id 不能为负数, 请稍后再试, /(ㄒoㄒ)/~~" + id;
    }

}
