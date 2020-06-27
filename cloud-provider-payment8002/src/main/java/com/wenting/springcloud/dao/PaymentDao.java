package com.wenting.springcloud.dao;

import com.wenting.springcloud.bean.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface PaymentDao {

    int create(Payment payment);

    Payment findPaymentById(@Param("id") Long id);

}
