package com.wenting.springcloud.controller;

import com.wenting.springcloud.bean.Payment;
import com.wenting.springcloud.service.PaymentService;
import com.wenting.springcloud.vo.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
public class PaymentController {

    @Resource
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @Autowired
    private DiscoveryClient discoveryClient;

    @PostMapping("/payment/create")
    public CommonResult createOrder(@RequestBody Payment payment) {
        int effect = paymentService.create(payment);
        log.info("[创建订单] orderId = {}", effect);
        if (effect > 0){
            return new CommonResult(200, "创建订单成功,serverPort" + serverPort , effect);
        }
        return new CommonResult(404, "创建订单失败,serverPort" + serverPort , null);
    }

    @GetMapping("/payment/get/{id}")
    public CommonResult<Payment> findPayment(@PathVariable("id") Long id) {
        Payment result = paymentService.findPaymentById(id);
        log.info("[查询订单] payment = {}", result);
        if (result != null){
            return new CommonResult(200, "查询订单成功,serverPort" + serverPort , result);
        }
        return new CommonResult(404, "查询订单失败, 查询id: " + id + "serverPort: " + serverPort, null);
    }


    @GetMapping("/payment/discovery")
    public Object discovery(){

        List<String> services = discoveryClient.getServices();
        for (String service : services) {
                log.info("******************service***************: {}", service);
        }
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for (ServiceInstance instance : instances) {
            log.info(instance.getServiceId() + "\t" + instance.getHost() + "\t" + instance.getUri());
        }
        return this.discoveryClient;

    }

    @GetMapping("/payment/lb")
    public String getPaymentLb(){
        return this.serverPort;
    }

    @GetMapping("/payment/timeout")
    public String paymentFeignTimeOut(){
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return this.serverPort;
    }


    @GetMapping("/payment/zipkin")
    public String paymentZipkin(){
        System.out.println("i is zipkin...");
        return "i is zipkin...";
    }
}
