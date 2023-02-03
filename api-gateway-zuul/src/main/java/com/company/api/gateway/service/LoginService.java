package com.company.api.gateway.service;

import com.company.api.gateway.model.LoginDto;
import com.company.api.gateway.model.UserInfo;
import com.company.api.gateway.util.JwtUtil;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    /**
     * 验证登录
     * @param user
     * @return
     */
    public LoginDto login(UserInfo user){
        LoginDto loginDto = new LoginDto();
        loginDto.setUserName(user.getUserName());
        loginDto.setResultCode(200);
        if(user==null){
            loginDto.setMsg("user is null");
            loginDto.setToken(null);
            return loginDto;
        }
        if(!"admin".equals(user.getUserName())||!"123456".equals(user.getPassword())){
            loginDto.setMsg("userName or password is error");
            loginDto.setToken(null);
            return loginDto;
        }
        String token = JwtUtil.generateToken(user);
        loginDto.setMsg("login succeed");
        loginDto.setToken(token);
        return loginDto;
    }
}
