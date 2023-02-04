package com.company.model;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RateLimiter {
    /**
     * 限流key(第一个参数限流的 key，这个仅仅是一个前缀，将来完整的 key 是这个前缀再加上接口方法的完整路径，共同组成限流 key，这个 key 将被存入到 Redis 中)
     */
    String key() default "rate_limit";

    /**
     * 限流时间,单位秒
     */
    int time() default 60;

    /**
     * 限流次数
     */
    int count() default 100;

    /**
     * 限流类型
     */
    LimitType limitType() default LimitType.DEFAULT;
}
