package com.wenting.springcloud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class OrderNacosController {

    @Value("${server-url.nacos-user-service}")
    private String SERVICE_NAME;

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/consumer/payment/nacos/{id}")
    public String order(@PathVariable("id") Integer id){
        return restTemplate.getForObject(SERVICE_NAME + "/payment/nacos/" + id, String.class);
    }


}
