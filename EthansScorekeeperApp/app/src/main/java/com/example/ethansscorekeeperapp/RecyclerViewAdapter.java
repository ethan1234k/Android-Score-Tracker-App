package com.example.ethansscorekeeperapp;

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

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    private Context mContext;
    private List<Round> mData;

    public RecyclerViewAdapter(Context mContext, List<Round> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        view = mInflater.inflate(R.layout.cardview_move, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.SetRow(position);
        holder.roundNum.setText(mData.get(position).getRoundNum());
        holder.moveScoreOne.setText(mData.get(position).getFirstScoreText());
        holder.moveScoreTwo.setText(mData.get(position).getSecondScoreText());

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        int row;
        TextView roundNum;
        EditText moveScoreOne;
        EditText moveScoreTwo;

        public MyViewHolder(View itemView) {
            super(itemView);

            roundNum = (TextView) itemView.findViewById(R.id.round_id);
            moveScoreOne = (EditText) itemView.findViewById(R.id.moveOne_id);
            moveScoreTwo = (EditText) itemView.findViewById(R.id.moveTwo_id);
        }

        public void SetRow(int r)
        {
            this.row = r;
            moveScoreOne.addTextChangedListener(new MyTextWatcher(row,1));
            moveScoreTwo.addTextChangedListener(new MyTextWatcher(row,2));
        }

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
            if ( round < 0 ) {
                return;
            }
            Round r = mData.get(round);
            if ( playerNum == 1 ) {
                r.setScoreOne(s.toString());
            }
            if ( playerNum == 2 ) {
                r.setScoreTwo(s.toString());
            }
        }

    }

}
