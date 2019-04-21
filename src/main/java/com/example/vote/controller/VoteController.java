package com.example.vote.controller;

import com.example.vote.entity.User;
import com.example.vote.entity.Vote;
import com.example.vote.service.VoteService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.jws.soap.SOAPBinding;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author white matter
 */
@RestController
public class VoteController {
    @Autowired
    private VoteService voteService;

    /**
     * 点击投票接口
     * @param college
     * @return
     */
    @GetMapping(value = "vote",produces = "application/json")
    public String vote(String college,String u_id){
        if (voteService.vote(college,u_id)){
            return "投票成功";
        }else {
            return "今日票数已用完";
        }
    }

    /**
     *
     * @return 所有学院的票数json
     */
    @GetMapping(value = "voteNumber",produces = "application/json")
    public String voteNumber(){
        List<Vote> list = voteService.selectCollege();
        Gson gson = new Gson();
        return gson.toJson(list);
    }

    /**
     * 用户还剩多少票
     * @param u_id
     * @return
     */
    @GetMapping(value = "achieve",produces = "application/json")
    public String selectUserVote(String u_id){
        User user = new User();
        user = voteService.selectUserVote(u_id);
        Gson gson = new Gson();
        return gson.toJson(user);
    }

    /**
     * 定时任务每日点更新每个用户的票数5票
     * @return 更新结果
     * 秒（0~59） 例如0/5表示每5秒
     * 分（0~59）
     * 时（0~23）
     * 日（0~31）的某天，需计算
     * 月（0~11）
     * 周几（ 可填1-7 或 SUN/MON/TUE/WED/THU/FRI/SAT）
     */
    @Scheduled(cron = "0 0 0 * * ?")
   @GetMapping(value = "updateDayVote",produces = "application/json")
    public String updateDayVote(){
        String result = voteService.updateDayVote();
        return result;
    }
    @GetMapping(value = "levelShort",produces = "application/json")
    public String levelShort(){
        Map<String,String> map = new HashMap<>(1000);
        map = voteService.prizeNumber();
        Gson gson = new Gson();
        return gson.toJson(map);
    }
}
