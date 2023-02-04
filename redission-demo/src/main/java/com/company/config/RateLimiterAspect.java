package com.company.config;

import com.company.utils.IpUtils;
import com.company.model.LimitType;
import com.company.model.RateLimiter;
import com.company.utils.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.lang.reflect.Method;
import java.util.Collections;
import java.util.List;

@Aspect
@Component
@Slf4j
public class RateLimiterAspect {

    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;

    @Autowired
    private RedisScript<Long> redisScript;

    @Before("@annotation(rateLimiter)")
    public void doBefore(JoinPoint joinPoint, RateLimiter rateLimiter) throws Throwable{
        String key = rateLimiter.key();
        int time = rateLimiter.time();
        int count = rateLimiter.count();
        String combineKey = getCombineKey(rateLimiter, joinPoint);
        List<Object> keys = Collections.singletonList(combineKey);
        try {
            Long number = redisTemplate.execute(redisScript, keys, count, time);
            if (number==null || number.intValue()>count){
                throw new ServiceException("访问过于频繁，请稍后重试");
            }
            log.info("限制请求'{}',当前请求'{}',缓存key'{}'", count, number.intValue(), key);
        }catch (ServiceException e){
            throw e;
        }catch (Exception e){
            throw new RuntimeException("服务器限流异常");
        }
    }

    public String getCombineKey(RateLimiter rateLimiter, JoinPoint joinPoint){
        StringBuffer stringBuffer = new StringBuffer(rateLimiter.key());
        if (rateLimiter.limitType() == LimitType.IP) {
            stringBuffer.append(IpUtils.getIpAddr(((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest())).append("-");
        }
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        Class<?> targetClass = method.getDeclaringClass();
        stringBuffer.append(targetClass.getName()).append("-").append(method.getName());
        return stringBuffer.toString();
    }
}
