package com.company.utils;

public class ServiceException extends Exception{

    private String message;

    public ServiceException(String message){
        super(message);
    }

}
