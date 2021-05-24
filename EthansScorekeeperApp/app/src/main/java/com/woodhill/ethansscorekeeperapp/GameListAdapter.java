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

public class GameListAdapter extends RecyclerView.Adapter<GameListAdapter.GameListViewHolder> {

    private Context mContext;
    private GameList mGameList;
    private MainActivity mMainActivity;
    int gameNum;

    public GameListAdapter (Context mContext, GameList mGameList, MainActivity mMainActivity) {
        this.mContext = mContext;
        this.mGameList = mGameList;
        this.mMainActivity = mMainActivity;
    }

    public GameListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        view = mInflater.inflate(R.layout.cardview_saved_games, parent, false);
        return new GameListViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
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
