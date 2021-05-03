package com.example.ethansscorekeeperapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.ethansscorekeeperapp.fragments.fragmentNamesFourPlayer;
import com.example.ethansscorekeeperapp.fragments.fragmentNamesThreePlayer;
import com.example.ethansscorekeeperapp.fragments.fragmentNamesTwoPlayer;
import com.example.ethansscorekeeperapp.fragments.fragmentScoresFourPlayer;
import com.example.ethansscorekeeperapp.fragments.fragmentScoresThreePlayer;
import com.example.ethansscorekeeperapp.fragments.fragmentScoresTwoPlayer;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

// comments
public class MainActivity extends AppCompatActivity {

    int numWelcomeButtonClicks = 0;

    int totalScoreLeft = 0;
    int totalScoreRight = 0;

    //Initialization for necessary ArrayLists
    List<Round> roundList = new ArrayList<>();
    List<String> playerNameList = new ArrayList<>();
    List<Integer> scoreList = new ArrayList<>();


    //Initialization statements for fragmentManager and fragments
    FragmentManager fragmentManager = getSupportFragmentManager();
    fragmentNamesTwoPlayer fragmentNamesTwoPlayer = new fragmentNamesTwoPlayer(this);
    fragmentNamesThreePlayer fragmentNamesThreePlayer = new fragmentNamesThreePlayer(this);
    fragmentNamesFourPlayer fragmentNamesFourPlayer = new fragmentNamesFourPlayer(this);
    com.example.ethansscorekeeperapp.fragments.fragmentScoresTwoPlayer fragmentScoresTwoPlayer = new fragmentScoresTwoPlayer();
    com.example.ethansscorekeeperapp.fragments.fragmentScoresThreePlayer fragmentScoresThreePlayer = new fragmentScoresThreePlayer();
    com.example.ethansscorekeeperapp.fragments.fragmentScoresFourPlayer fragmentScoresFourPlayer = new fragmentScoresFourPlayer();

    ScoresRecyclerViewAdapter myScoresRecyclerViewAdapter;
    RecyclerView myScoresRecyclerView;

    GameListAdapter myGameListAdapter;
    RecyclerView myGameListRecyclerView;

    Game game = new Game();
    GameList gameList;

    int gameNum = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        gameList = GameList.load(this);
        if (gameList == null) {
            gameList = new GameList();
        }

        //TODO
        //Use AlertDialog for saving the game to replace the save screen
        //TODO


        setContentView(R.layout.home_screen);
        setGameListRecyclerView();
        //setContentView(R.layout.score_table_recycler_view);



        //roundList.add(new Round("Round 1",0, 0));
        //game.setRoundList(roundList);

        /*
        roundList.add(new Round("Round 2", 7, 3));
        roundList.add(new Round("Round 3", 4, 0));
        roundList.add(new Round("Round 4", 2, 5));
        roundList.add(new Round("Round 5", 6, 1));
        roundList.add(new Round("Round 6", 2, 4));
        roundList.add(new Round("Round 7",1, 4));
        roundList.add(new Round("Round 8", 7, 3));
        roundList.add(new Round("Round 9", 4, 0));
        roundList.add(new Round("Round 10", 2, 5));
        roundList.add(new Round("Round 11", 6, 1));
        roundList.add(new Round("Round 12", 2, 4));
         */



        /*
        RecyclerView myRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        myRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        RecyclerViewAdapter myAdapter = new RecyclerViewAdapter(this, roundList);
        //myRecyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        myRecyclerView.setAdapter(myAdapter);
         */



        //TextView txtAppName = findViewById(R.id.appName);
        //txtAppName.setText("Universal Scorekeeper");

        //TextView txtAppDescription = findViewById(R.id.appDescription);
        //txtAppDescription.setText("Welcome to the Universal Scorekeep app. You can use this app to keep track of anything you desire.");

    }

    public void onStop() {
        gameList.save(this);
        super.onStop();
    }

    public void onNewGameClick(View view) {
        createSetNumPlayersDialog();
    }

    public void createSetNumPlayersDialog() {
        AlertDialog.Builder numPlayersBuilder = new AlertDialog.Builder(this);
        numPlayersBuilder.setTitle("How Many Players?");
        String[] playerNums = {"Two", "Three", "Four"};
        int checkedItem = 0;
        numPlayersBuilder.setSingleChoiceItems(playerNums, checkedItem, null);
        numPlayersBuilder.setPositiveButton(
                "Confirm",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        int selectedPosition = ((AlertDialog)dialog).getListView().getCheckedItemPosition();
                        if (selectedPosition == 0) {
                            twoPlayersSelected();
                        }
                        if (selectedPosition == 1) {
                            threePlayersSelected();
                        }
                        if (selectedPosition == 2) {
                            fourPlayersSelected();
                        }
                        dialog.cancel();
                    }
                });

        numPlayersBuilder.setNegativeButton(
                "Cancel",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog dialog = numPlayersBuilder.create();
        dialog.show();
    }

    //Method that gets called when the user is trying to load a saved game, and sets and prepares the necessary elements and variables for the game
    public void loadGame(int savedGameNum) {
        gameNum = savedGameNum;
        game = gameList.getGameAtIndex(savedGameNum);
        setGameFragments(game.getNumPlayers());
        roundList = game.getRoundList();
        setContentView(R.layout.score_table_recycler_view);
        setScoresRecyclerViewLayout();
        playerNameList = game.getPlayerNameList();
        initializeGameTotalScoresList(game.getNumPlayers());
    }

    public void deleteGame(int gameNum) {
        gameList.deleteGame(gameNum);
        setGameListRecyclerView();
    }

    public void callSetPlayerNames() {
        setPlayerNames(game.getNumPlayers(), playerNameList);
    }

    //Method that initializes the program for two players
    public void twoPlayersSelected() {
        setContentView(R.layout.score_table_recycler_view);
        game.setNumPlayers(2);

        initializePlayerNameList(2);
        initializeGameTotalScoresList(2);
        initializeFirstRound(2);
        setScoresRecyclerViewLayout();
        setGameFragments(2);
    }

    //Method that initializes the program for three players
    public void threePlayersSelected() {
        setContentView(R.layout.score_table_recycler_view);
        game.setNumPlayers(3);

        initializePlayerNameList(3);
        initializeGameTotalScoresList(3);
        initializeFirstRound(3);
        setScoresRecyclerViewLayout();
        setGameFragments(3);
    }

    //Method that initializes the program for four players
    public void fourPlayersSelected() {
        setContentView(R.layout.score_table_recycler_view);
        game.setNumPlayers(4);

        initializePlayerNameList(4);
        initializeGameTotalScoresList(4);
        initializeFirstRound(4);
        setScoresRecyclerViewLayout();
        setGameFragments(4);
    }

    //Method that takes the number of players in the game, and correctly sets the fragments for the score table UI
    public void setGameFragments (int numPlayers) {
        if (numPlayers == 2) {
            fragmentManager.beginTransaction()
                    .replace(R.id.frameLayoutFragmentPlayerNames, fragmentNamesTwoPlayer)
                    .replace(R.id.frameLayoutFragmentScores, fragmentScoresTwoPlayer)
                    .commit();
        }
        if (numPlayers == 3) {
            fragmentManager.beginTransaction()
                    .replace(R.id.frameLayoutFragmentPlayerNames, fragmentNamesThreePlayer)
                    .replace(R.id.frameLayoutFragmentScores, fragmentScoresThreePlayer)
                    .commit();
        }
        if (numPlayers == 4) {
            fragmentManager.beginTransaction()
                    .replace(R.id.frameLayoutFragmentPlayerNames, fragmentNamesFourPlayer)
                    .replace(R.id.frameLayoutFragmentScores, fragmentScoresFourPlayer)
                    .commit();
        }
    }

    //Method that
    public void setPlayerNames(int numPlayers, List<String> playerNameList) {
        if (numPlayers == 2) {
            fragmentNamesTwoPlayer.setPlayerNames(playerNameList);
        }
        if (numPlayers == 3) {
            fragmentNamesThreePlayer.setPlayerNames(playerNameList);
        }
        if (numPlayers == 4) {
            fragmentNamesFourPlayer.setPlayerNames(playerNameList);
        }
    }

    //Method that sets the initial player names
    public void initializePlayerNameList (int players) {
        if (players > 1) {
            playerNameList.add("Player 1");
            playerNameList.add("Player 2");
        }
        if (players > 2) {
            playerNameList.add("Player 3");
        }
        if (players > 3) {
            playerNameList.add("Player 4");
        }
        game.setPlayerNameList(playerNameList);
    }

    //Method that sets the initial totalScoresList with 0's
    public void initializeGameTotalScoresList (int players) {
        for (int i = 0; i < players; i++) {
            scoreList.add(0);
        }
    }

    //Method that sets up the first round in the score table
    public void initializeFirstRound (int players) {
        List<Integer> initializeRound = new ArrayList<>();
        for (int i = 0; i < players; i++) {
            initializeRound.add(0);
        }
        roundList.add(new Round("Round 1", initializeRound));
    }

    public void setGameListRecyclerView() {
        myGameListRecyclerView = (RecyclerView) findViewById(R.id.recyclerViewGames);
        myGameListRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        myGameListAdapter = new GameListAdapter(this, gameList, this);
        myGameListRecyclerView.setAdapter(myGameListAdapter);
    }

    //Initializes the recycler view
    public void setScoresRecyclerViewLayout() {
        myScoresRecyclerView = (RecyclerView) findViewById(R.id.recyclerViewScores);
        myScoresRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        myScoresRecyclerViewAdapter = new ScoresRecyclerViewAdapter(this, roundList, game.getNumPlayers());
        myScoresRecyclerView.setAdapter(myScoresRecyclerViewAdapter);
    }

    //Method that gets called when the user adds a new row
    public void onAddRowClick(View view) {
        String lastRound = roundList.get(roundList.size() - 1).roundNum;
        int lastRoundNum = Integer.parseInt(lastRound.substring(6));
        lastRoundNum++;
        String currentRound = "Round " + lastRoundNum;
        List<Integer> initializeRound = new ArrayList<>();

        for (int i = 0; i < game.getNumPlayers(); i++) {
            initializeRound.add(0);
        }
        roundList.add(new Round(currentRound, initializeRound));
        game.setRoundList(roundList);
        rvAdapterUpdate(myScoresRecyclerViewAdapter, myScoresRecyclerView);
    }

    //Method that updates the rvAdapter in case new rounds are added
    public void rvAdapterUpdate(ScoresRecyclerViewAdapter myAdapter, RecyclerView myRecyclerView) {
        myRecyclerView.setAdapter(myAdapter);
    }

    //Method that adds up the total scores for a column
    public void onCalculateClick(View view) {
        int numPlayers = game.getNumPlayers();
        calculate(numPlayers);
    }

    public void calculate(int players) {
        if (players == 2) {
            scoreList.set(0, getColumnTotalScore(roundList, 0));
            scoreList.set(1, getColumnTotalScore(roundList, 1));
            fragmentScoresTwoPlayer.setScores(scoreList.get(0), scoreList.get(1));
        }
        if (players == 3) {
            scoreList.set(0, getColumnTotalScore(roundList, 0));
            scoreList.set(1, getColumnTotalScore(roundList, 1));
            scoreList.set(2, getColumnTotalScore(roundList, 2));
            fragmentScoresThreePlayer.setScores(scoreList.get(0), scoreList.get(1), scoreList.get(2));
        }
        if (players == 4) {
            scoreList.set(0, getColumnTotalScore(roundList, 0));
            scoreList.set(1, getColumnTotalScore(roundList, 1));
            scoreList.set(2, getColumnTotalScore(roundList, 2));
            scoreList.set(3, getColumnTotalScore(roundList, 3));
            fragmentScoresFourPlayer.setScores(scoreList.get(0), scoreList.get(1), scoreList.get(2), scoreList.get(3));
        }
    }

    public int getColumnTotalScore (List<Round> roundList, int column) {
        int total = 0;

        for (int i = 0; i < roundList.size(); i++) {
            total += roundList.get(i).getScoreList().get(column);
        }

        return total;
    }

    //Method that gets the current player names from the edit texts for the name fields
    public void savePlayerNames(int players) {
        if (players == 2) {
            game.setPlayerNameList(fragmentNamesTwoPlayer.getPlayerNames());
        }
        if (players == 3) {
            game.setPlayerNameList(fragmentNamesThreePlayer.getPlayerNames());
        }
        if (players == 4) {
            game.setPlayerNameList(fragmentNamesFourPlayer.getPlayerNames());
        }
    }

    //When the save button is clicked, calls the createSaveGameDialog method
    public void onSaveClick(View view) {
        createSaveGameDialog();
    }

    //Method that creates the save game alert dialog. This alert asks the user for the game name, and stores it.
    public void createSaveGameDialog() {
        AlertDialog.Builder saveNameBuilder = new AlertDialog.Builder(this);
        final EditText gameName = new EditText(this);
        saveNameBuilder.setMessage("Enter Game Name Below");
        saveNameBuilder.setView(gameName);
        saveNameBuilder.setCancelable(true);

        saveNameBuilder.setPositiveButton(
                "Confirm",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        if (gameName.getText().length() != 0) {
                            saveConfirmation(String.valueOf(gameName.getText()));
                            dialog.cancel();
                        }
                    }
                });

        saveNameBuilder.setNegativeButton(
                "Cancel",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alertSaveName = saveNameBuilder.create();
        alertSaveName.show();
    }

    public void saveConfirmation (String gameName) {
        game.setGameName(String.valueOf(gameName));
        prepareGameForSave();
        saveGameToFile(this);
        setContentView(R.layout.home_screen);
        //Resets all variables and lists
        game = new Game();
        roundList = new ArrayList<>();
        playerNameList = new ArrayList<>();
        gameNum = -1;
        setGameListRecyclerView();
        detachFragments(game.getNumPlayers());
    }

    public void prepareGameForSave() {
        savePlayerNames(game.getNumPlayers());
        game.setRoundList(roundList);
        Date d = new Date();
        game.setGameDate(d);
    }

    public void saveGameToFile (Context context) {
        if (gameNum == -1) {
            gameList.addSavedGame(game);
        } else {
            gameList.setGame(gameNum, game);
        }
    }

    public void detachFragments(int numPlayers) {
        if (numPlayers == 2) {
            fragmentManager.beginTransaction()
                    .remove(fragmentNamesTwoPlayer)
                    .remove(fragmentScoresTwoPlayer)
                    .commit();
        }
        if (numPlayers == 3) {
            fragmentManager.beginTransaction()
                    .remove(fragmentNamesThreePlayer)
                    .remove(fragmentScoresThreePlayer)
                    .commit();
        }
        if (numPlayers == 4) {
            fragmentManager.beginTransaction()
                    .remove(fragmentNamesFourPlayer)
                    .remove(fragmentScoresFourPlayer)
                    .commit();
        }
    }

    //TODO end of new code with recycler view


















    public void onBtnClick (View view) {
        if (numWelcomeButtonClicks == 0) {
            TextView txtAppDescription = findViewById(R.id.appDescription);
            txtAppDescription.setText("You can enter in your scores for each round in the table, and the total score will be displayed at the bottom of the screen.");
            Button welcomeButton = findViewById(R.id.welcomeButton);
            welcomeButton.setText("Finish");
            numWelcomeButtonClicks ++;
        } else {
            Button welcomeButton = findViewById(R.id.welcomeButton);
            setContentView(R.layout.score_table);

            //TODO Create a new method for setting all the text for the TextViews


            TextView txtPlayerOne = findViewById(R.id.playerOneName);
            txtPlayerOne.setText("Player 1");

            TextView txtPlayerTwo = findViewById(R.id.playerTwoName);
            txtPlayerTwo.setText("Player 2");

            TextView txtTotalScoreOne = findViewById(R.id.totalScoreLeft);
            txtTotalScoreOne.setText(Integer.toString(totalScoreLeft));

            TextView txtTotalScoreTwo = findViewById(R.id.totalScoreRight);
            txtTotalScoreTwo.setText(Integer.toString(totalScoreRight));

            TextView txtRoundOne = findViewById(R.id.roundOne);
            txtRoundOne.setText("Round 1");

            TextView txtRoundTwo = findViewById(R.id.roundTwo);
            txtRoundTwo.setText("Round 2");

            //setEditTexts();

        }
    }

    /*
    public void setEditTexts() {
        EditText edtTxtLeftScoreOne = findViewById(R.id.leftScoreOne);
        EditText edtTxtLeftScoreTwo = findViewById(R.id.leftScoreTwo);
        EditText edtTxtRightScoreOne = findViewById(R.id.rightScoreOne);
        EditText edtTxtRightScoreTwo = findViewById(R.id.rightScoreTwo);

        edtTxtLeftScoreOne.addTextChangedListener(textWatcher);
        edtTxtLeftScoreTwo.addTextChangedListener(textWatcher);
        edtTxtRightScoreOne.addTextChangedListener(textWatcher);
        edtTxtRightScoreTwo.addTextChangedListener(textWatcher);

    } */

    public void setTotalScores() {
        EditText edtTxtLeftScoreOne = findViewById(R.id.leftScoreOne);
        EditText edtTxtLeftScoreTwo = findViewById(R.id.leftScoreTwo);
        EditText edtTxtRightScoreOne = findViewById(R.id.rightScoreOne);
        EditText edtTxtRightScoreTwo = findViewById(R.id.rightScoreTwo);


        totalScoreLeft = totalScores(edtTxtLeftScoreOne.getText().toString(), edtTxtLeftScoreTwo.getText().toString());
        totalScoreRight = totalScores(edtTxtRightScoreOne.getText().toString(), edtTxtRightScoreTwo.getText().toString());

        TextView txtTotalScoreOne = findViewById(R.id.totalScoreLeft);
        txtTotalScoreOne.setText(Integer.toString(totalScoreLeft));

        TextView txtTotalScoreTwo = findViewById(R.id.totalScoreRight);
        txtTotalScoreTwo.setText(Integer.toString(totalScoreRight));
    }

    //Method that takes to strings, if they are an int it adds them up to the total score
    public int totalScores(String firstScore, String secondScore) {
        int totalScore = 0;
        if (isInteger(firstScore)) {
            totalScore += Integer.parseInt(firstScore);
        }

        if (isInteger(secondScore)) {
            totalScore += Integer.parseInt(secondScore);
        }
        return totalScore;
    }

    //Method that checks if a string is an Integer
    public static boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
        } catch(NumberFormatException e) {
            return false;
        } catch(NullPointerException e) {
            return false;
        }
        // only got here if we didn't return false
        return true;
    }

}