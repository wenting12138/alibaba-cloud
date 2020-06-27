package com.wenting.springcloud.feignclient;

import com.wenting.springcloud.bean.Payment;
import com.wenting.springcloud.vo.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(value = "CLOUD-PAYMENT-SERVICE")
public interface PaymentFeignService {

    @GetMapping("/payment/get/{id}")
    CommonResult<Payment> findPayment(@PathVariable("id") Long id);

    @GetMapping("/payment/timeout")
    public String paymentFeignTimeOut();
}
