package com.wenting.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope // 自动刷新config
public class MyController {

    @Value("${user1.name2}")
    private String uname;

    @Value("${server.port}")
    private String serverport;

    @GetMapping("/configinfo")
    public String getconfigname(){
        return "*****message: " + this.uname + "*********port: " +this.serverport;
    }
}
