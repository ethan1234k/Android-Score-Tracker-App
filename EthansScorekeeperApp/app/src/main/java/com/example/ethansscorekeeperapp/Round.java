package com.example.ethansscorekeeperapp;

import java.util.ArrayList;
import java.util.List;

public class Round {

    public String roundNum;
    List<Integer> scoreList = new ArrayList<>();


    public Round(String roundNum, List<Integer> scoreList){
        this.roundNum = roundNum;
        this.scoreList = scoreList;
    }

    public void setScore(List<Integer> scoreList) {
        this.scoreList = scoreList;
    }


    /*public void setScore(String oneText, String twoText)
    {
        try {
            this.scoreOne = Integer.parseInt(oneText);
        } catch (Exception e) {
            // when it's not a number, set to 0
            this.scoreOne = 0;
        }

        try {
            this.scoreTwo = Integer.parseInt(twoText);
        } catch (Exception e) {
            // when it's not a number, set to 0
            this.scoreTwo = 0;
        }
    } */

    public String getRoundNum() {
        return roundNum;
    }

    public List<Integer> getScoreList() {
        return scoreList;
    }

}
