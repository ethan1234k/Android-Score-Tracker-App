package com.example.ethansscorekeeperapp;

import com.example.ethansscorekeeperapp.Round;

import java.util.ArrayList;
import java.util.List;

public class Game {

    String playerNameOne;
    String playerNameTwo;
    List<Round> roundList = new ArrayList<>();

    public Game () {

    }

    public Game (String playerNameOne, String playerNameTwo) {
        this.playerNameOne = playerNameOne;
        this.playerNameTwo = playerNameOne;
    }

    public String getPlayerNameOne() {
        return playerNameOne;
    }

    public String getPlayerNameTwo() {
        return playerNameTwo;
    }

    public List<Round> getRoundList() {
        return roundList;
    }

    public void setPlayerNameOne(String playerNameOne) {
        this.playerNameOne = playerNameOne;
    }

    public void setPlayerNameTwo(String playerNameTwo) {
        this.playerNameTwo = playerNameTwo;
    }

    public void setRoundList(List<Round> roundList) {
        this.roundList = roundList;
    }
}
