package com.wenting.order.controller;


import com.wenting.order.domain.Order;
import com.wenting.order.service.OrderService;
import com.wenting.order.vo.CommonResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class OrderController {

    @Resource
    private OrderService orderService;


    @GetMapping("/order/create")
    public CommonResult createOrder(Order order){
        orderService.create(order);
        return new CommonResult(200, "订单创建成功");
    }

}
