package com.example.vote.mapper;

import com.example.vote.entity.User;
import com.example.vote.service.SignUpService;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author white matter
 */
@Mapper
@Component
public interface SignInMapper {

    /**
     * 登录
     * @param user
     * @return
     */
    @Select("select * from vote where u_id = #{u_id} and u_password = #{password} ")
    boolean signIn(User user);

    @Update("update user SET token = #{token} WHERE u_id = #{u_id}")
    boolean updateToken(User user);


}
