package com.example.vote.mapper;

import com.example.vote.entity.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

/**
 * @author white matter
 */
@Mapper
@Component
public interface LogoutMapper {

    /**
     * 注销登录
     * @param user
     * @return
     */
    @Update("update user set token = ? where u_id = ?")
    boolean logout(User user);
}
