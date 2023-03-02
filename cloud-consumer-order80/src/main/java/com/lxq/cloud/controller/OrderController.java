package com.lxq.cloud.controller;

import com.lxq.cloud.entities.CommonResult;
import com.lxq.cloud.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author Yikair
 */
@RestController
@Slf4j
public class OrderController {

    //    public static final String PAYMENT_URL = "http://localhost:8001"; // 单机版
    public static final String PAYMENT_URL = "http://CLOUD-PAYMENT-SERVICE"; // 集群版-调整为 eureka 上的微服务名称

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping(value = "consumer/payment/create", method = RequestMethod.POST)
    public CommonResult<Payment> create(Payment payment) {
        return restTemplate.postForObject(PAYMENT_URL + "/payment/create", payment, CommonResult.class);
    }

    @RequestMapping(value = "consumer/payment/get/{id}", method = RequestMethod.GET)
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id) {
        return restTemplate.getForObject(PAYMENT_URL + "/payment/get/" + id, CommonResult.class);
    }

}
