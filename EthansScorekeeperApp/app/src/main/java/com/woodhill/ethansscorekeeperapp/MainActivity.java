package com.woodhill.ethansscorekeeperapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.woodhill.ethansscorekeeperapp.fragments.fragmentNamesFourPlayer;
import com.woodhill.ethansscorekeeperapp.fragments.fragmentNamesThreePlayer;
import com.woodhill.ethansscorekeeperapp.fragments.fragmentNamesTwoPlayer;
import com.woodhill.ethansscorekeeperapp.fragments.fragmentScoresFourPlayer;
import com.woodhill.ethansscorekeeperapp.fragments.fragmentScoresThreePlayer;
import com.woodhill.ethansscorekeeperapp.fragments.fragmentScoresTwoPlayer;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

// comments
public class MainActivity extends AppCompatActivity {

    //Initialization for necessary ArrayLists
    List<Round> roundList = new ArrayList<>();
    List<String> playerNameList = new ArrayList<>();
    List<Integer> scoreList = new ArrayList<>();


    //Initialization statements for fragmentManager and fragments
    FragmentManager fragmentManager = getSupportFragmentManager();
    com.woodhill.ethansscorekeeperapp.fragments.fragmentNamesTwoPlayer fragmentNamesTwoPlayer = new fragmentNamesTwoPlayer(this);
    com.woodhill.ethansscorekeeperapp.fragments.fragmentNamesThreePlayer fragmentNamesThreePlayer = new fragmentNamesThreePlayer(this);
    com.woodhill.ethansscorekeeperapp.fragments.fragmentNamesFourPlayer fragmentNamesFourPlayer = new fragmentNamesFourPlayer(this);
    com.woodhill.ethansscorekeeperapp.fragments.fragmentScoresTwoPlayer fragmentScoresTwoPlayer = new fragmentScoresTwoPlayer();
    com.woodhill.ethansscorekeeperapp.fragments.fragmentScoresThreePlayer fragmentScoresThreePlayer = new fragmentScoresThreePlayer();
    com.woodhill.ethansscorekeeperapp.fragments.fragmentScoresFourPlayer fragmentScoresFourPlayer = new fragmentScoresFourPlayer();

    ScoresRecyclerViewAdapter myScoresRecyclerViewAdapter;
    RecyclerView myScoresRecyclerView;

    GameListAdapter myGameListAdapter;
    RecyclerView myGameListRecyclerView;

    Game game = new Game();
    GameList gameList;

    int gameNum = -1;

    private AdView mAdView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        gameList = GameList.load(this);
        if (gameList == null) {
            gameList = new GameList();
        }

        setContentView(R.layout.home_screen);

        initializeBannerAds();
        setGameListRecyclerView();
    }

    public void initializeBannerAds() {
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
    }

    public void setupUI(View view) {
        // Set up touch listener for non-text box views to hide keyboard.
        if (!(view instanceof EditText)) {
            view.setOnTouchListener(new View.OnTouchListener() {
                public boolean onTouch(View v, MotionEvent event) {
                    hideSoftKeyboard(MainActivity.this);
                    return false;
                }
            });
        }

        //If a layout container, iterate over children and seed recursion.
        if (view instanceof ViewGroup) {
            for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
                View innerView = ((ViewGroup) view).getChildAt(i);
                setupUI(innerView);
            }
        }
    }

    public static void hideSoftKeyboard(Activity activity) {
        InputMethodManager inputMethodManager =
                (InputMethodManager) activity.getSystemService(
                        Activity.INPUT_METHOD_SERVICE);
        if (inputMethodManager.isAcceptingText()) {
            inputMethodManager.hideSoftInputFromWindow(
                    activity.getCurrentFocus().getWindowToken(),
                    0
            );
        }
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
        numPlayersBuilder.setTitle(this.getResources().getString(R.string.how_many_players));
        String[] playerNums = {this.getResources().getString(R.string.two), this.getResources().getString(R.string.three), this.getResources().getString(R.string.four)};
        int checkedItem = 0;
        numPlayersBuilder.setSingleChoiceItems(playerNums, checkedItem, null);
        numPlayersBuilder.setPositiveButton(
                this.getResources().getString(R.string.confirm),
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        int selectedPosition = ((AlertDialog) dialog).getListView().getCheckedItemPosition();
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
                this.getResources().getString(R.string.cancel),
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
        setupUI(findViewById(R.id.rvScoresParent));
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
        setupUI(findViewById(R.id.rvScoresParent));
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
        setupUI(findViewById(R.id.rvScoresParent));
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
        setupUI(findViewById(R.id.rvScoresParent));
        game.setNumPlayers(4);

        initializePlayerNameList(4);
        initializeGameTotalScoresList(4);
        initializeFirstRound(4);
        setScoresRecyclerViewLayout();
        setGameFragments(4);
    }

    //Method that takes the number of players in the game, and correctly sets the fragments for the score table UI
    public void setGameFragments(int numPlayers) {
        if (numPlayers == 2) {
            fragmentManager.beginTransaction()
                    .add(R.id.frameLayoutFragmentPlayerNames, fragmentNamesTwoPlayer)
                    .add(R.id.frameLayoutFragmentScores, fragmentScoresTwoPlayer)
                    .commit();
        }
        if (numPlayers == 3) {
            fragmentManager.beginTransaction()
                    .add(R.id.frameLayoutFragmentPlayerNames, fragmentNamesThreePlayer)
                    .add(R.id.frameLayoutFragmentScores, fragmentScoresThreePlayer)
                    .commit();
        }
        if (numPlayers == 4) {
            fragmentManager.beginTransaction()
                    .add(R.id.frameLayoutFragmentPlayerNames, fragmentNamesFourPlayer)
                    .add(R.id.frameLayoutFragmentScores, fragmentScoresFourPlayer)
                    .commit();
        }
    }

    //Method that gets the correct fragment and sets the player names for the fragment
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
    public void initializePlayerNameList(int players) {
        if (players > 1) {
            playerNameList.add(this.getResources().getString(R.string.player_one));
            playerNameList.add(this.getResources().getString(R.string.player_two));
        }
        if (players > 2) {
            playerNameList.add(this.getResources().getString(R.string.player_three));
        }
        if (players > 3) {
            playerNameList.add(this.getResources().getString(R.string.player_four));
        }
        game.setPlayerNameList(playerNameList);
    }

    //Method that sets the initial totalScoresList with 0's
    public void initializeGameTotalScoresList(int players) {
        for (int i = 0; i < players; i++) {
            scoreList.add(0);
        }
    }

    //Method that sets up the first round in the score table
    public void initializeFirstRound(int players) {
        List<Integer> initializeRound = new ArrayList<>();
        for (int i = 0; i < players; i++) {
            initializeRound.add(0);
        }
        roundList.add(new Round(this.getResources().getString(R.string.round) + " 1", initializeRound));
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
        myScoresRecyclerViewAdapter = new ScoresRecyclerViewAdapter(this, roundList, this, game.getNumPlayers());
        myScoresRecyclerView.setAdapter(myScoresRecyclerViewAdapter);
    }

    //Method that gets called when the user adds a new row
    public void onAddRowClick(View view) {
        String lastRound = roundList.get(roundList.size() - 1).roundNum;
        int lastRoundNum = Integer.parseInt(lastRound.substring(lastRound.indexOf(" ") + 1));
        lastRoundNum++;
        String currentRound = this.getResources().getString(R.string.round) + " " + lastRoundNum;
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

    public void calculate() {
        if (game.getNumPlayers() == 2) {
            scoreList.set(0, getColumnTotalScore(roundList, 0));
            scoreList.set(1, getColumnTotalScore(roundList, 1));
            fragmentScoresTwoPlayer.setScores(scoreList.get(0), scoreList.get(1));
        }
        if (game.getNumPlayers() == 3) {
            scoreList.set(0, getColumnTotalScore(roundList, 0));
            scoreList.set(1, getColumnTotalScore(roundList, 1));
            scoreList.set(2, getColumnTotalScore(roundList, 2));
            fragmentScoresThreePlayer.setScores(scoreList.get(0), scoreList.get(1), scoreList.get(2));
        }
        if (game.getNumPlayers() == 4) {
            scoreList.set(0, getColumnTotalScore(roundList, 0));
            scoreList.set(1, getColumnTotalScore(roundList, 1));
            scoreList.set(2, getColumnTotalScore(roundList, 2));
            scoreList.set(3, getColumnTotalScore(roundList, 3));
            fragmentScoresFourPlayer.setScores(scoreList.get(0), scoreList.get(1), scoreList.get(2), scoreList.get(3));
        }
    }

    public int getColumnTotalScore(List<Round> roundList, int column) {
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
        if (gameNum > -1) {
            gameName.setText(game.getGameName());
        }
        int maxLength = 10;
        gameName.setFilters(new InputFilter[]{new InputFilter.LengthFilter(maxLength)});
        saveNameBuilder.setMessage(this.getResources().getString(R.string.enter_game));
        saveNameBuilder.setView(gameName);
        saveNameBuilder.setCancelable(true);
        saveNameBuilder.setPositiveButton(
                this.getResources().getString(R.string.confirm),
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        if (gameName.getText().length() != 0) {
                            saveConfirmation(String.valueOf(gameName.getText()));
                            dialog.cancel();
                        }
                    }
                });

        saveNameBuilder.setNegativeButton(
                this.getResources().getString(R.string.cancel),
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alertSaveName = saveNameBuilder.create();
        alertSaveName.show();
    }

    public void saveConfirmation(String gameName) {
        game.setGameName(String.valueOf(gameName));
        prepareGameForSave();
        saveGameToFile(this);
        setContentView(R.layout.home_screen);
        initializeBannerAds();
        //Resets all variables and lists
        setGameListRecyclerView();
        detachFragments(game.getNumPlayers());
        game = new Game();
        roundList = new ArrayList<>();
        playerNameList = new ArrayList<>();
        gameNum = -1;
    }

    public void prepareGameForSave() {
        savePlayerNames(game.getNumPlayers());
        game.setRoundList(roundList);
        Date d = new Date();
        game.setGameDate(d);
    }

    public void saveGameToFile(Context context) {
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

    //Method that brings up an alert dialog which tells the user about the app
    public void onHelpClick(View view) {
        AlertDialog.Builder helpDialogBuilder = new AlertDialog.Builder(this);
        helpDialogBuilder.setMessage(this.getResources().getString(R.string.help_text));
        helpDialogBuilder.setCancelable(true);

        helpDialogBuilder.setPositiveButton(
                this.getResources().getString(R.string.ok),
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alertSaveName = helpDialogBuilder.create();
        alertSaveName.show();
    }
}