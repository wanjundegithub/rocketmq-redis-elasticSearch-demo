package com.company.service;

import com.company.model.Merchandise;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Component
@Slf4j
public class UserService {

    @Autowired
    private OrderService orderService;

    private static ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1, 1, 2, TimeUnit.SECONDS,new ArrayBlockingQueue<>(100));

    public String shop(Merchandise merchandise){
        threadPoolExecutor.submit(() -> {
            orderService.order(merchandise, (Runnable) () -> log.info("shop success"), (Runnable) () ->                                         log.info("shop failure"));
             });
        return "shop";
    }
}
