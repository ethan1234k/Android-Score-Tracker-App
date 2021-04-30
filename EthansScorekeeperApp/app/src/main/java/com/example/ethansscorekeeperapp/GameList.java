package com.example.ethansscorekeeperapp;

import android.content.Context;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class GameList implements Serializable{

    ArrayList<Game> gameList = new ArrayList<>();

    public GameList() {

    }

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


}
