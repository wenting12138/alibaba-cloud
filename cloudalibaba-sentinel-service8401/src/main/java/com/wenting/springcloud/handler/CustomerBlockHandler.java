package com.wenting.springcloud.handler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.wenting.springcloud.bean.Payment;
import com.wenting.springcloud.vo.CommonResult;

public class CustomerBlockHandler {

    public static CommonResult handlerException(BlockException e){
        return new CommonResult(200, "自定义限流, global Exception- -------1");
    }

    public static CommonResult handlerException2(BlockException e){
        return new CommonResult(200, "自定义限流, global Exception- -------2");
    }
}
