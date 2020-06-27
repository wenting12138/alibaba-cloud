package com.wenting.springcloud.controller;

import com.wenting.springcloud.bean.Payment;
import com.wenting.springcloud.vo.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import com.wenting.springcloud.service.PaymentService;

@RestController
@Slf4j
public class PaymentController {

    @Resource
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @PostMapping("/payment/create")
    public CommonResult createOrder(@RequestBody Payment payment) {
        int effect = paymentService.create(payment);
        log.info("[创建订单] orderId = {}", effect);
        if (effect > 0){
            return new CommonResult(200, "创建订单成功,serverPort" + serverPort , effect);
        }
        return new CommonResult(404, "创建订单失败,serverPost" + serverPort , null);
    }

    @GetMapping("/payment/get/{id}")
    public CommonResult<Payment> findPayment(@PathVariable("id") Long id) {
        Payment result = paymentService.findPaymentById(id);
        log.info("[查询订单] payment = {}", result);
        if (result != null){
            return new CommonResult(200, "查询订单成功,serverPost" + serverPort , result);
        }
        return new CommonResult(404, "查询订单失败, 查询id: " + id + "serverPort: " + serverPort, null);
    }

    @GetMapping("/payment/lb")
    public String getPaymentLb(){
        return this.serverPort;
    }
}
