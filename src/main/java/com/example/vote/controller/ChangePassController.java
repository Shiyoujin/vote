package com.example.vote.controller;

import com.example.vote.service.ChangePassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author white matter
 */
@RestController
public class ChangePassController {
    @Autowired
    private ChangePassService changePassService;

    @GetMapping(value = "changPass",produces = "application/json")
    public String changePass(String u_id){
        if (changePassService.changePass(u_id)){
            return "修改密码成功";
        }else {
            return "修改密码失败";
        }

    }
}
