package com.company.service;

import com.company.model.Merchandise;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@Slf4j
public class OrderService {

    public boolean order(Merchandise merchandise, Runnable successRunnable,
                         Runnable failureRunnable) {
        String name = merchandise.getName();
        BigDecimal price = merchandise.getPrice();
        //设定库存
        Long stock = 100L;
        if (stock > 0&&price.compareTo(new BigDecimal(9.99))<0) {
            successRunnable.run();
            return true;
        }
        failureRunnable.run();
        return false;
    }
}
