package com.wenting.springcloud.service;

import com.wenting.springcloud.bean.Payment;
import org.apache.ibatis.annotations.Param;

public interface PaymentService {


    int create(Payment payment);

    Payment findPaymentById(@Param("id") Long id);

}
