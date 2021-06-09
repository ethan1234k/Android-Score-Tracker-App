package com.woodhill.ethansscorekeeperapp;

import android.content.Context;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class GameList implements Serializable{

    ArrayList<Game> gameList = new ArrayList<>();

    public GameList() {
        //Empty Constructor
    }

    //Below: Getters and Setters
    public ArrayList<Game> getGameList() {
        return gameList;
    }

    public void setGameList(ArrayList<Game> gameList) {
        this.gameList = gameList;
    }

    public void setGame(int index, Game game) {
        gameList.set(index, game);
    }

    public void addSavedGame(Game game) {
        gameList.add(game);
    }

    //Uses the date time and game name to return the title for the game
    public String getGameDisplay (int index) {
        Game game = gameList.get(index);
        String gameDisplay;
        DateFormat formatter = new SimpleDateFormat("M/dd/yyyy h:mm a");
        gameDisplay = String.valueOf(index + 1) + ".    " + game.getGameName() + "     " + formatter.format(game.getGameDate());;
        return gameDisplay;
    }

    public Game getGameAtIndex (int index) {
        return gameList.get(index);
    }

    public int getSize() {
        return gameList.size();
    }

    //Method to save the gamelist to a local file
    public void save (Context context) {
        try {
            File saveFile = new File(context.getFilesDir(), "gameList.gme");
            FileOutputStream fileOut = new FileOutputStream(saveFile, false);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(this);
            objectOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Method to load the gamelist from the local file
    public static GameList load (Context context) {
        GameList gameList = null;

        try {
            File saveFile = new File(context.getFilesDir(), "gameList.gme");
            FileInputStream fileIn = new FileInputStream(saveFile);
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);
            gameList = (GameList) objectIn.readObject();
            objectIn.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return gameList;
    }

    //Deletes a game from the gamelist
    public void deleteGame(int index) {
        gameList.remove(index);
    }

}
