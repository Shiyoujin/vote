package com.example.vote.service;

import com.example.vote.entity.User;
import com.example.vote.mapper.SignInMapper;
import com.example.vote.util.JwtUtils;
import com.example.vote.util.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author white matter
 */
@Service
public class SignInService {
    @Autowired
    private SignInMapper signInMapper;

    public boolean SignIn(String u_id,String pass){
        String token = JwtUtils.jwtSignIn(u_id);
        String password = MD5Utils.inputPassToDbPass(pass,"1a2b3c4d");
        User user = new User();
        user.setU_id(u_id);
        user.setU_password(password);
        user.setU_password(password);
        user.setToken(token);

        if (signInMapper.signIn(user)){
            //token存放本本地
            //此后也可以解析 token获取 u_id，或者过滤器校验
            signInMapper.updateToken(user);
            return true;
        } else {
            return false;
        }
    }
}
