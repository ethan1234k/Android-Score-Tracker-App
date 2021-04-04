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


    public void setScore(int scoreOne, int scoreTwo) {
        this.scoreOne = scoreOne;
        this.scoreTwo = scoreTwo;
    }

    public void setScore(String oneText, String twoText)
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
    }

    public void setScoreOne(String scoreOne) {
        try {
            this.scoreOne = Integer.parseInt(scoreOne);
        } catch (Exception e) {
            // when it's not a number, set it to 0
            this.scoreOne = 0;
        }
    }

    public void setScoreTwo(String scoreTwo) {
        try {
            this.scoreTwo = Integer.parseInt(scoreTwo);
        } catch (Exception e) {
            // when it's not a number, set it to 0
            this.scoreTwo = 0;
        }
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
