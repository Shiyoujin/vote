package com.example.vote.controller;

import com.example.vote.service.SignInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author white matter
 */
@RestController
public class SignInController {
    @Autowired
    SignInService signInService;

    @GetMapping(value = "signIn",produces = "application/json")
    public String signIn(String u_id,String password){
        if (signInService.SignIn(u_id,password)){
            return "登录成功";
        }else {
            return "账号或者密码错误";
        }
    }

}
