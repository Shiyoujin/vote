package com.example.vote.service;

import com.example.vote.entity.User;
import com.example.vote.mapper.ChangePassMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author white matter
 */
@Service
public class ChangePassService {
    @Autowired
    private ChangePassMapper changePassMapper;

    public boolean changePass(String u_id){
        User user = new User();
        user.setU_id(u_id);
        if (changePassMapper.changPass(user)){
            return true;
        }else {
            return false;
        }
    }
}
