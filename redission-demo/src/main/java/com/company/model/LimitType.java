package com.company.model;

public enum LimitType {
    /**
     * 默认全局限流
     */
    DEFAULT,

    /**
     * 根据请求IP限流
     */
    IP
}
