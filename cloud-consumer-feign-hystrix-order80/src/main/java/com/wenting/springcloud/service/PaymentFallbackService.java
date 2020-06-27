package com.wenting.springcloud.service;


import org.springframework.stereotype.Component;

@Component
public class PaymentFallbackService implements OrderService {

    @Override
    public String paymentInfo_Ok(Integer id) {
        return "PaymentFallbackService  paymentInfo_Ok  fallback ... /(ㄒoㄒ)/~~";
    }

    @Override
    public String paymentInfo_Timeout(Integer id) {
        return "PaymentFallbackService   paymentInfo_Timeout ... /(ㄒoㄒ)/~~";
    }

}
