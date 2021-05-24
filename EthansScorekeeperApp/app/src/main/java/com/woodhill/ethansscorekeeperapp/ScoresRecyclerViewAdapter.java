package com.woodhill.ethansscorekeeperapp;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ScoresRecyclerViewAdapter extends RecyclerView.Adapter<ScoresRecyclerViewAdapter.ScoresViewHolder> {

    private Context mContext;
    private List<Round> mData;
    private MainActivity mMainActivity;
    int players;

    public ScoresRecyclerViewAdapter(Context mContext, List<Round> mData, MainActivity mMainActivity, int players) {
        this.mContext = mContext;
        this.mData = mData;
        this.mMainActivity = mMainActivity;
        this.players = players;
    }

    public ScoresViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        if (players == 2) {
            view = mInflater.inflate(R.layout.cardview_move_two_player, parent, false);
        } else if (players == 3) {
            view = mInflater.inflate(R.layout.cardview_move_three_player, parent, false);
        } else {
            view = mInflater.inflate(R.layout.cardview_move_four_player, parent, false);
        }
        return new ScoresViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ScoresViewHolder holder, int position) {

        holder.setIsRecyclable(false);
        holder.SetRow(position);
        holder.roundNum.setText(mData.get(position).getRoundNum());
        holder.moveScoreOne.setText(String.valueOf(mData.get(position).getScoreList().get(0)));
        holder.moveScoreTwo.setText(String.valueOf(mData.get(position).getScoreList().get(1)));
        if (players > 2) {
            holder.moveScoreThree.setText(String.valueOf(mData.get(position).getScoreList().get(2)));
        }
        if (players > 3) {
            holder.moveScoreFour.setText(String.valueOf(mData.get(position).getScoreList().get(3)));
        }

    }

    public class ScoresViewHolder extends RecyclerView.ViewHolder {

        int row;
        TextView roundNum;
        EditText moveScoreOne;
        EditText moveScoreTwo;
        EditText moveScoreThree;
        EditText moveScoreFour;

        public ScoresViewHolder(View itemView) {
            super(itemView);

            roundNum = (TextView) itemView.findViewById(R.id.round_id);
            moveScoreOne = (EditText) itemView.findViewById(R.id.moveOne_id);
            moveScoreTwo = (EditText) itemView.findViewById(R.id.moveTwo_id);
            if(players > 2) {
                moveScoreThree = (EditText) itemView.findViewById(R.id.moveThree_id);
            }
            if(players > 3) {
                moveScoreFour = (EditText) itemView.findViewById(R.id.moveFour_id);
            }
        }

        public void SetRow(int r)
        {
            this.row = r;
            moveScoreOne.addTextChangedListener(new MyTextWatcher(row,1));
            moveScoreTwo.addTextChangedListener(new MyTextWatcher(row,2));
            if (players > 2) {
                moveScoreThree.addTextChangedListener(new MyTextWatcher(row,3));
            }
            if (players > 3) {
                moveScoreFour.addTextChangedListener(new MyTextWatcher(row,4));
            }
        }
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }


    public class MyTextWatcher implements TextWatcher
    {
        int round;
        int playerNum;

        public MyTextWatcher(int round, int num)
        {
            this.round = round;
            this.playerNum = num;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            if ( s.toString() == null || s.toString().equals(""))
                return;
            if ( round < 0 ) {
                return;
            }
            Round r = mData.get(round);
            r.getScoreList().set(playerNum - 1, Integer.valueOf(s.toString()));
            mMainActivity.calculate();
        }

    }

}
