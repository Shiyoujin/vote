package com.example.vote.controller;

import com.example.vote.service.SignUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author white matter
 */
@RestController
public class SignUpController {
    @Autowired
    private SignUpService signUpService;

    @GetMapping(value = "signUp",produces = "application/json")
    public String signUp(String u_id,String u_name,String password){
        if (signUpService.signUp(u_id,u_name,password)){
            return "注册成功";
        } else {
            return "注册失败";
        }
    }
}
