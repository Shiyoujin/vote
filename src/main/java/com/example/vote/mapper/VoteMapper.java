package com.example.vote.mapper;


import com.example.vote.entity.User;
import com.example.vote.entity.Vote;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author white matter
 */
@Mapper
@Component
public interface VoteMapper {

    @Update("update vote set vote = #{vote} where college = #{college}")
    boolean updateVote(Vote vote);

    @Select("select voteNumber form vote where college = #{college}")
    String selectVoteN(Vote vote);

    @Select("select college,voteNumber from vote")
    List<Vote> selectCollege();

    @Select("select vote from user where u_id = #{u_id}")
    int selectUserVote(String u_id);

    @Update("update user set vote = #{vote}from user where u_id = #{u_id}")
    boolean updateUserVote(String u_id,int vote);

    @Update("update user set vote = #{vote}")
    boolean updateDayVote(int vote);

}
