package com.wenting.springcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.wenting.springcloud.bean.Payment;
import com.wenting.springcloud.handler.CustomerBlockHandler;
import com.wenting.springcloud.vo.CommonResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RateLimitController {


    @RequestMapping("/byResource")
    @SentinelResource(value = "byResource", blockHandler = "handleException")
    public CommonResult byResource(){
        return new CommonResult(200, "按资源名称限流测试ok", new Payment(2020L, "serial001"));
    }

    public CommonResult handleException(BlockException e){
        return new CommonResult(444, e.getClass().getCanonicalName() + "\t 服务不可用");
    }


    @SentinelResource(value = "byUrl")
    @GetMapping("/rateLimit/byUrl")
    public CommonResult byUrl() {
        return new CommonResult(200, "按url限流测试ok", new Payment(2020L, "serial002"));
    }

    @SentinelResource(value = "customerBlockHandler",
            blockHandlerClass = CustomerBlockHandler.class,
            blockHandler = "handlerException2")
    @GetMapping("/rateLimit/customerBlockHandler")
    public CommonResult customerBlockHandler() {
        return new CommonResult(200, "自定义限流", new Payment(2020L, "serial003"));
    }
}
