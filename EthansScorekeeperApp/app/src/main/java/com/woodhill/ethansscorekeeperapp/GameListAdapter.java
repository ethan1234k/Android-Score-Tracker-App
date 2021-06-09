package com.woodhill.ethansscorekeeperapp;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

//Recycler view for game list
public class GameListAdapter extends RecyclerView.Adapter<GameListAdapter.GameListViewHolder> {

    private Context mContext;
    private GameList mGameList;
    private MainActivity mMainActivity;
    int gameNum;

    //Game List Adapter setter
    public GameListAdapter (Context mContext, GameList mGameList, MainActivity mMainActivity) {
        this.mContext = mContext;
        this.mGameList = mGameList;
        this.mMainActivity = mMainActivity;
    }

    //Method to inflate the cardview
    public GameListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        view = mInflater.inflate(R.layout.cardview_saved_games, parent, false);
        return new GameListViewHolder(view);
    }

    //Method to bind view holder
    public void onBindViewHolder(@NonNull GameListViewHolder holder, int position) {
        holder.setIsRecyclable(false);
        String gameText = mGameList.getGameDisplay(position);
        holder.savedGame.setText(gameText);
    }

    public class GameListViewHolder extends RecyclerView.ViewHolder {

        Button savedGame;
        FloatingActionButton deleteGameButton;

        public GameListViewHolder(View itemView) {
            super(itemView);
            //Listens for the user to load a game, and loads the roper game
            savedGame = (Button) itemView.findViewById(R.id.savedGame_id);
            savedGame.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    String gameText = (String) savedGame.getText();
                    gameText = gameText.substring(0, gameText.indexOf('.'));
                    gameNum = Integer.parseInt(gameText) - 1;
                    mMainActivity.loadGame(gameNum);
                }
            });

            //Listens for an on click on the delete button, and calls the delete method
            deleteGameButton = (FloatingActionButton) itemView.findViewById(R.id.deleteGameButton_id);
            deleteGameButton.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    String gameText = (String) savedGame.getText();
                    gameText = gameText.substring(0, gameText.indexOf('.'));
                    gameNum = Integer.parseInt(gameText) - 1;
                    mMainActivity.deleteGame(gameNum);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mGameList.getSize();
    }
}
