package com.wenting.springcloud.controller;


import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FlowLimitController {

    @GetMapping("/testA")
    public String testA() throws InterruptedException {
        Thread.sleep(1000);
        return "***********testA";
    }

    @GetMapping("/testB")
    public String testB(){
        System.out.println(Thread.currentThread().getName() + "\t" + "...testB");
        return "***********testB**********";
    }

    @GetMapping("/testD")
    public String testD() throws InterruptedException {
//        Thread.sleep(1000);
        System.out.println("testD ***** 测试异常");
        int i = 10 / 0;
        return "********TestD";
    }

    @GetMapping("/testHotKey")
    @SentinelResource(value = "testHotKey", blockHandler = "deal_testHotKey") // 热点参数限流
    public String testHotKey(@RequestParam(value = "p1", required = false) String p1,
                             @RequestParam(value = "p2", required = false) String p2) {
        return "testHotKey";
    }

    public String deal_testHotKey(String p1, String p2, BlockException e){
        return "---------deal_testHotKey-----/(ㄒoㄒ)/~~";
    }

}
