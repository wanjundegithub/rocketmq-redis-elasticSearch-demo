package com.company.api.gateway.model;

public class LoginDto {
    private long resultCode;
    private String msg;
    private String token;
    private String userName;

    public long getResultCode() {
        return resultCode;
    }

    public void setResultCode(long resultCode) {
        this.resultCode = resultCode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
