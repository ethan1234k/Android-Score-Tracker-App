package com.woodhill.ethansscorekeeperapp;

import java.util.List;
import java.io.Serializable;

public class Round implements Serializable{

    public String roundNum;
    List<Integer> scoreList;

    //Round setter
    public Round(String roundNum, List<Integer> scoreList){
        this.roundNum = roundNum;
        this.scoreList = scoreList;
    }

    //Getters and setters
    public void setScore(List<Integer> scoreList) {
        this.scoreList = scoreList;
    }


    public String getRoundNum() {
        return roundNum;
    }

    public List<Integer> getScoreList() {
        return scoreList;
    }

}
