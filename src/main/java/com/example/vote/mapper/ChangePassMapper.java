package com.example.vote.mapper;

import com.example.vote.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

/**
 * @author white matter
 */
@Mapper
@Component
public interface ChangePassMapper {
    /**
     * 修改密码
     * @param user
     * @return
     */
    @Update("update user set u_password = ? where u_id = ?")
    boolean changPass(User user);
}
