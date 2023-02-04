package com.company.service;

import com.company.utils.ServiceException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalException {

    @ExceptionHandler(ServiceException.class)
    public Map<String, Object> serviceException(ServiceException serviceException){
        HashMap<String, Object> map =new HashMap<>();
        map.put("status", 429);
        map.put("message", serviceException.getMessage());
        return  map;
    }
}
