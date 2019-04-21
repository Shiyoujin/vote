package com.example.vote.service;

import com.example.vote.entity.User;
import com.example.vote.mapper.LogoutMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author white matter
 */
@Service
public class LogoutService {
    @Autowired
    private LogoutMapper logoutMapper;

    public boolean logout(String u_id){
        User user = new User();
        user.setU_id(u_id);
        return logoutMapper.logout(user);
    }
}
