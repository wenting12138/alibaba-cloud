package com.wenting.springcloud.service.impl;

import com.wenting.springcloud.bean.Payment;
import com.wenting.springcloud.dao.PaymentDao;
import com.wenting.springcloud.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Resource
    private PaymentDao paymentDao;

    @Transactional
    public int create(Payment payment) {
        return paymentDao.create(payment);
    }

    @Override
    public Payment findPaymentById(Long id) {
        return paymentDao.findPaymentById(id);
    }
}
