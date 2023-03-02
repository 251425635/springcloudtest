package com.lxq.cloud.controller;

import com.lxq.cloud.entities.CommonResult;
import com.lxq.cloud.entities.Payment;
import com.lxq.cloud.service.PaymentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Yikair
 */
@Api(tags = "支付管理", description = "支付接口")
@Slf4j
@RestController
public class PaymentController {
    @Autowired
    private PaymentService paymentService;

    @Autowired
    private DiscoveryClient discoveryClient;

    @Value("${server.port}")
    private String serverPort;

    @ApiOperation("支付插入")
    @RequestMapping(value = "/payment/create", method = RequestMethod.POST)
    public CommonResult create(@RequestBody Payment payment) {
        int result = paymentService.create(payment);
        log.info("插入结果：" + result);
        if (result > 0) {
            return new CommonResult(200, "插入成功, serverPort：" + serverPort, result);
        } else {
            return new CommonResult(444, "插入失败", null);
        }/**/
    }

    @ApiOperation("支付查询")
    @RequestMapping(value = "/payment/get/{id}", method = RequestMethod.GET)
    public CommonResult getPaymentById(@PathVariable("id") Long id) {
        Payment payment = paymentService.getPaymentById(id);
        log.info("查询结果：" + payment);
        if (payment != null) {
            return new CommonResult(200, "查询成功 serverPort：" + serverPort, payment);
        } else {
            return new CommonResult(444, "查询失败, 查询ID：" + id, null);
        }
    }

    @RequestMapping(value = "/payment/discover", method = RequestMethod.GET)
    public DiscoveryClient discovery() {
        List<String> services = discoveryClient.getServices();
        for (String service : services) {
            log.info("Eureka 的服务信息：" + "\t" + service);
            List<ServiceInstance> instances = discoveryClient.getInstances(service);
            for (ServiceInstance instance : instances) {
                log.info(service + " 的实例信息：" + "\t" + instance.getServiceId() + "\t" + instance.getInstanceId() + "\t" + instance.getHost() + "\t" + instance.getPort() + "\t" + instance.getUri());
            }
        }
        return discoveryClient;
    }
}
