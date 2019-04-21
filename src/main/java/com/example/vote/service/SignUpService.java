package com.example.vote.service;

import com.example.vote.entity.User;
import com.example.vote.mapper.SignUpMapper;
import com.example.vote.util.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author white matter
 */
@Service
public class SignUpService {
    @Autowired
    private SignUpMapper signUpMapper;

    public boolean isExistSignUp(String u_id){
        User user = new User();
        user.setU_id(u_id);
        return signUpMapper.isExistSignUp(user);
    }

    public boolean signUp(String u_id,String u_name,String pass){
        User user = new User();
        if (isExistSignUp(u_id)){
            String password = MD5Utils.inputPassToDbPass(pass,"1a2b3c4d");
            user.setU_password(password);
            user.setU_id(u_id);
            user.setU_name(u_name);
            return signUpMapper.signUp(user);
        }else {
            return false;
        }
    }
}
