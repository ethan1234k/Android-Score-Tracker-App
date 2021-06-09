package com.woodhill.ethansscorekeeperapp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.io.Serializable;

public class Game implements Serializable{

    String gameName;
    Date gameDate;
    int numPlayers;
    List<String> playerNameList = new ArrayList<>();
    List<Round> roundList = new ArrayList<>();


    public Game () {
        //Empty Constructor
    }

    //Game Constructor
    public Game (int numPlayers, List<String> playerNameList, List<Round> roundList) {
        this.numPlayers = numPlayers;
        this.playerNameList = playerNameList;
        this.roundList = roundList;
    }

    //Below: Getters and setters for Game Object
    public String getGameName() {
        return gameName;
    }

    public Date getGameDate() {
        return gameDate;
    }

    public int getNumPlayers() {
        return numPlayers;
    }

    public List<String> getPlayerNameList() {
        return playerNameList;
    }

    public List<Round> getRoundList() {
        return roundList;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public void setGameDate(Date gameDate) {
        this.gameDate = gameDate;
    }

    public void setNumPlayers(int numPlayers) {
        this.numPlayers = numPlayers;
    }

    public void setPlayerNameList(List<String> playerNameList) {
        this.playerNameList = playerNameList;
    }

    public void setRoundList(List<Round> roundList) {
        this.roundList = roundList;
    }
}
