package com.example.vote.entity;

import lombok.Data;
import org.springframework.context.annotation.Bean;

/**
 * @author white matter
 */
@Data
public class User {

    public String u_id;

    public String u_name;

    public String u_password;

    public String token;

    public int Vote;

    public User(String u_id,String u_name,String u_password){
        this.u_id = u_id;
        this.u_name = u_name;
        this.u_password = u_password;
    }
    public User(){

    }
    }
