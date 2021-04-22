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

    public GameListAdapter (Context mContext, GameList mGameList) {
        this.mContext = mContext;
        this.mGameList = mGameList;
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
        }
    }

    @Override
    public int getItemCount() {
        return mGameList.getSize();
    }
}
