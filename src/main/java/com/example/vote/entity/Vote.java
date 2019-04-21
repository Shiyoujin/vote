package com.example.vote.entity;

import lombok.Data;

import java.util.List;

/**
 * @author white matter
 */
@Data
public class Vote {

    public int id;

    public String college;

    public int voteNumber;

    public List<String> collegeList;



}
