package com.wenting.springcloud.controller;


import com.wenting.springcloud.bean.Payment;
import com.wenting.springcloud.feignclient.PaymentFeignService;
import com.wenting.springcloud.vo.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class OrderFeignController {

    @Autowired
    private PaymentFeignService paymentFeignService;

    @GetMapping("/consumer/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id) {
        return paymentFeignService.findPayment(id);
    }

    @GetMapping("/consumer/payment/timeout")
    public String getFeigntimeout(){
        return paymentFeignService.paymentFeignTimeOut();
    }

}
