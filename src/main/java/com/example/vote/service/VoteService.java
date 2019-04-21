package com.example.vote.service;

import com.example.vote.entity.User;
import com.example.vote.entity.Vote;
import com.example.vote.mapper.VoteMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.jws.soap.SOAPBinding;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author white matter
 */
@Service
public class VoteService {
    @Autowired
    private VoteMapper voteMapper;

    public boolean vote(String college,String u_id){
        Vote vote = new Vote();
        User user = new User();
        vote.setCollege(college);
        String vNumber = voteMapper.selectVoteN(vote);
        int voteUser = voteMapper.selectUserVote(u_id);
        voteUser-=1;
        int number = Integer.parseInt(vNumber);
        number+=1;
        vote.setVoteNumber(number);
        if (voteUser==0){
            return false;
        }else if (voteMapper.updateVote(vote)){
            if (voteMapper.updateUserVote(u_id,voteUser)){
                return true;
            }
            return false;
        }else {
            return false;
        }
    }

    public List<Vote> selectCollege(){
        List<Vote> list = new ArrayList<>();
        list = voteMapper.selectCollege();
        return list;
    }

    public User selectUserVote(String u_id){
        User user = new User();
        int number = voteMapper.selectUserVote(u_id);
        user.setVote(number);
        return user;
    }

    public String updateDayVote(){
        int dayNumber = 5;
        if (voteMapper.updateDayVote(dayNumber)){
            return "每日更新票数成功";
        }else {
            return "每日更新票数失败";
        }
    }

    public Map<String,String> prizeNumber(){
        List<Vote> list = voteMapper.selectCollege();
        Map<String,String> map = new HashMap(1000);
        int all = 0;
        int prizeLevel = 0;
        int shortNumber = 0;
        for (Vote vote : list){
            int number = vote.getVoteNumber();
            all+=number;
        }
        for (; prizeLevel>=0;prizeLevel++){
            all-=2019;
        }
        shortNumber = 2019-all;
        map.put("prizeLevel", String.valueOf(prizeLevel));
        map.put("shortNumber",String.valueOf(shortNumber));
        return map;
    }
}
