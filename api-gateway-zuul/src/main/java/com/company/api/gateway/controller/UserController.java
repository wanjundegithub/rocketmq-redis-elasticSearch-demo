package com.company.api.gateway.controller;


import com.company.api.gateway.model.LoginDto;
import com.company.api.gateway.model.UserInfo;
import com.company.api.gateway.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/jwt")
public class UserController {

    @Autowired
    private LoginService loginService;

    @PostMapping("/login")
    public LoginDto loginRequest(@RequestBody UserInfo userInfo){
        return loginService.login(userInfo);
    }

}
