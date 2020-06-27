package com.wenting.springcloud.controller;

import com.wenting.springcloud.service.MessageProvider;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;

@RestController
public class MessageProviderController {

    @Resource
    private MessageProvider messageProvider;

    @GetMapping("/sendMessage")
    public String send(){
        return messageProvider.end();
    }

}
