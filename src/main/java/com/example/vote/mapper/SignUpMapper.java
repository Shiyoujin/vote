package com.example.vote.mapper;

import com.example.vote.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

/**
 * @author white matter
 */
@Mapper
@Component
public interface SignUpMapper {
    /**
     * 注册
     * @param user
     * @return boolean
     */
    @Insert("insert into user (u_id,u_name,u_password) value (#{u_id},#{u_name},#{u_password})")
    boolean signUp(User user);

    /**
     * 检验是否注册过
     * @param user
     * @return boolean
     */
    @Select("select u_id from user where u_id = #{u_id}")
    boolean isExistSignUp(User user);
}
