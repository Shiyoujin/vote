package com.example.vote.controller;

import com.example.vote.service.LogoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author white matter
 */
@RestController
public class LogoutController {
    @Autowired
    LogoutService logoutService;

    @GetMapping(value = "logout",produces = "application/json")
    public String logout(String u_id, HttpServletRequest request){
        if (logoutService.logout(u_id)){
            request.getSession().invalidate();
            return "注销成功";
        } else {
            return "注销失败";
        }
    }
}
