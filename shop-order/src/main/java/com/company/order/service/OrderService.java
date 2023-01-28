package com.company.order.service;

import com.company.order.model.Order;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    public void saveOrder(Order order){
        rocketMQTemplate.convertAndSend("order-topic", order);
    }
}
