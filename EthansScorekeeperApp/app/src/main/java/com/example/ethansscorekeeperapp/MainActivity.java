package com.example.ethansscorekeeperapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Fragment;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

// comments
public class MainActivity extends AppCompatActivity {

    int numWelcomeButtonClicks = 0;

    String leftScoreOne;
    String leftScoreTwo;
    String rightScoreOne;
    String rightScoreTwo;

    int totalScoreLeft = 0;
    int totalScoreRight = 0;

    //Actual variables and updated code
    List<Round> roundList = new ArrayList<>();
    List<String> playerNameList = new ArrayList<>();
    List<Integer> scoreList = new ArrayList<>();

    FragmentManager fragmentManager = getSupportFragmentManager();
    fragmentRVTwoPlayer fragmentTwoPlayer = new fragmentRVTwoPlayer();
    fragmentRVThreePlayer fragmentThreePlayer = new fragmentRVThreePlayer();
    fragmentRVFourPlayer fragmentFourPlayer = new fragmentRVFourPlayer();




    int leftScore = 0;
    int rightScore = 0;

    RecyclerViewAdapter myAdapter;
    RecyclerView myRecyclerView;

    Game game = new Game();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //TODO TESTING HERE
        setContentView(R.layout.home_screen);
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

    public void onNewGameClick(View view) {
        setContentView(R.layout.set_player_num);
    }

    public void onTwoPlayerClick(View view) {
        setContentView(R.layout.score_table_recycler_view);
        game.setNumPlayers(2);
        playerNameList.add("Player 1");
        playerNameList.add("Player 2");
        game.setPlayerNameList(playerNameList);
        scoreList.add(0);
        initializeGameTotalScoresList(2);

        List<Integer> initializeRound = new ArrayList<>();
        for (int i = 0; i < game.getNumPlayers(); i++) {
            initializeRound.add(0);
        }
        roundList.add(new Round("Round 1", initializeRound));

        setRecyclerViewLayout();
        //roundList.add(new Round("Round 1", ))

        fragmentManager.beginTransaction()
                .replace(R.id.frameLayoutFragmentPlayerNames, fragmentTwoPlayer)
                .commit();

    }

    public void onThreePlayerClick(View view) {
        setContentView(R.layout.score_table_recycler_view);
        game.setNumPlayers(3);
        playerNameList.add("Player 1");
        playerNameList.add("Player 2");
        playerNameList.add("Player 3");
        game.setPlayerNameList(playerNameList);
        initializeGameTotalScoresList(3);

        List<Integer> initializeRound = new ArrayList<>();
        for (int i = 0; i < game.getNumPlayers(); i++) {
            initializeRound.add(0);
        }
        roundList.add(new Round("Round 1", initializeRound));

        setRecyclerViewLayout();

        fragmentManager.beginTransaction()
                .replace(R.id.frameLayoutFragmentPlayerNames, fragmentThreePlayer)
                .commit();


    }

    public void onFourPlayerClick(View view) {
        setContentView(R.layout.score_table_recycler_view);
        game.setNumPlayers(4);
        playerNameList.add("Player 1");
        playerNameList.add("Player 2");
        playerNameList.add("Player 3");
        playerNameList.add("Player 4");
        game.setPlayerNameList(playerNameList);
        initializeGameTotalScoresList(4);

        List<Integer> initializeRound = new ArrayList<>();
        for (int i = 0; i < game.getNumPlayers(); i++) {
            initializeRound.add(0);
        }
        roundList.add(new Round("Round 1", initializeRound));

        setRecyclerViewLayout();

        fragmentManager.beginTransaction()
                .replace(R.id.frameLayoutFragmentPlayerNames, fragmentFourPlayer)
                .commit();


    }

    public void initializeGameTotalScoresList (int players) {
        for (int i = 0; i < players; i++) {
            scoreList.add(0);
        }
    }

    //Needs Update
    //TODO Change all the edit texts and setting for multiple players
    public void setRecyclerViewLayout() {
        myRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        myRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        myAdapter = new RecyclerViewAdapter(this, roundList, game.getNumPlayers());
        //myRecyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        myRecyclerView.setAdapter(myAdapter);

        //EditText playerOneName = findViewById(R.id.playerOneName);
        //game.setPlayerNameOne(String.valueOf(playerOneName.getText()));
        //EditText playerTwoName = findViewById(R.id.playerTwoName);
        //game.setPlayerNameTwo(String.valueOf(playerTwoName.getText()));
        //game.setRoundList(roundList);

        //playerOneName.addTextChangedListener(new myTextWatcher(1));
        //playerTwoName.addTextChangedListener(new myTextWatcher(2));
    }

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
        rvAdapterUpdate(myAdapter, myRecyclerView);
    }

    public void rvAdapterUpdate(RecyclerViewAdapter myAdapter, RecyclerView myRecyclerView) {
        myRecyclerView.setAdapter(myAdapter);
    }

    //Needs update
    public void onCalculateClick(View view) {
        int numPlayers = game.getNumPlayers();
        if (numPlayers == 2) {
            scoreList.set(0, getColumnTotalScore(roundList, 0));
            scoreList.set(1, getColumnTotalScore(roundList, 1));
        } else if (numPlayers == 3) {
            scoreList.set(0, getColumnTotalScore(roundList, 0));
            scoreList.set(1, getColumnTotalScore(roundList, 1));
            scoreList.set(2, getColumnTotalScore(roundList, 2));
        } else {
            scoreList.set(0, getColumnTotalScore(roundList, 0));
            scoreList.set(1, getColumnTotalScore(roundList, 1));
            scoreList.set(2, getColumnTotalScore(roundList, 2));
            scoreList.set(3, getColumnTotalScore(roundList, 3));
        }


        //leftScore = getLeftTotalScore(game.getRoundList());
        //rightScore = getRightTotalScore(game.getRoundList());
        //setTotalScores(leftScore, rightScore);
    }

    public int getColumnTotalScore (List<Round> roundList, int column) {
        int total = 0;

        for (int i = 0; i < roundList.size(); i++) {
            total += roundList.get(i).getScoreList().get(column);
        }

        return total;
    }

    //Needs update
    public int getLeftTotalScore (List<Round> roundList) {

        int leftTotal = 0;

        for (int i = 0; i < roundList.size(); i++) {
            //leftTotal += roundList.get(i).scoreOne;
        }

        return leftTotal;

    }

    //Needs update
    public int getRightTotalScore (List<Round> roundList) {

        int rightTotal = 0;

        for (int i = 0; i < roundList.size(); i++) {
            //rightTotal += roundList.get(i).scoreTwo;
        }

        return rightTotal;
    }

    //Needs update
    public void setTotalScores(int leftScore, int rightScore) {
        TextView totalLeft = findViewById(R.id.totalScoreLeft);
        TextView totalRight = findViewById(R.id.totalScoreRight);

        totalLeft.setText(String.valueOf(leftScore));
        totalRight.setText(String.valueOf(rightScore));
    }


    public class myTextWatcher implements TextWatcher {

        int playerNum;

        public myTextWatcher(int playerNum) {
            this.playerNum = playerNum;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            if ( playerNum < 0 ) {
                return;
            }
            if (playerNum == 1) {
                playerNameList.set(0, String.valueOf(s));
                game.setPlayerNameList(playerNameList);
            }
            if (playerNum == 2) {
                playerNameList.set(1, String.valueOf(s));
                game.setPlayerNameList(playerNameList);
            }
            if (playerNum == 3) {
                playerNameList.set(2, String.valueOf(s));
                game.setPlayerNameList(playerNameList);
            }
            if (playerNum == 4) {
                playerNameList.set(3, String.valueOf(s));
                game.setPlayerNameList(playerNameList);
            }
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