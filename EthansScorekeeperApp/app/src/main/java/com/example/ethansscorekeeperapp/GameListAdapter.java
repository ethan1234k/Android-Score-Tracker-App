package com.example.ethansscorekeeperapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class GameListAdapter extends RecyclerView.Adapter<GameListAdapter.GameListViewHolder> {

    private Context mContext;
    private GameList mGameList;
    private MainActivity mMainActivity;
    int gameToLoad;

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

    public void onBindViewHolder(@NonNull GameListViewHolder holder, int position) {
        holder.setIsRecyclable(false);
        String gameText = mGameList.getGameDisplay(position);
        holder.savedGame.setText(gameText);
    }

    public class GameListViewHolder extends RecyclerView.ViewHolder {

        Button savedGame;

        public GameListViewHolder(View itemView) {
            super(itemView);
            //Button is initialized but can't correctly set the text of the button at all!!
            savedGame = (Button) itemView.findViewById(R.id.savedGame_id);
            savedGame.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    String buttonText = (String) savedGame.getText();
                    buttonText = buttonText.substring(0, buttonText.indexOf('.'));
                    gameToLoad = Integer.parseInt(buttonText) - 1;
                    mMainActivity.loadGame(gameToLoad);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mGameList.getSize();
    }
}
