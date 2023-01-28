package com.company.user.service;

import com.alibaba.fastjson.JSONObject;
import com.company.user.model.Order;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@org.apache.rocketmq.spring.annotation.RocketMQMessageListener(consumerGroup = "user-group", topic = "order-topic")
public class RocketMQMessageListener implements RocketMQListener<Order> {
    @Override
    public void onMessage(Order order) {
        log.info("用户微服务收到了订单信息：{}", JSONObject.toJSONString(order));
    }
}
