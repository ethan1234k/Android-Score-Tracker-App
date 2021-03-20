package com.example.ethansscorekeeperapp;

public class Round {

    public String roundNum;
    public int scoreOne;
    public int scoreTwo;

    public Round(String roundNum, Integer scoreOne, Integer scoreTwo){
        this.roundNum = roundNum;
        this.scoreOne = scoreOne;
        this.scoreTwo = scoreTwo;

    }


    public void setScore(String roundNum, int scoreOne, int scoreTwo) {
        this.roundNum = roundNum;
        this.scoreOne = scoreOne;
        this.scoreTwo = scoreTwo;

    }

    public String getRoundNum() {
        return roundNum;
    }

    public int getFirstScore() {
        return scoreOne;
    }

    public int getSecondScore() {
        return scoreTwo;
    }

    public String getFirstScoreText() {
        return String.valueOf(scoreOne);
    }

    public String getSecondScoreText() {
        return String.valueOf(scoreTwo);
    }

}
