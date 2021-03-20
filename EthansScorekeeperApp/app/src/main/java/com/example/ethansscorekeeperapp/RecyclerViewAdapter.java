package com.example.ethansscorekeeperapp;

import android.content.Context;
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

        holder.roundNum.setText(mData.get(position).getRoundNum());
        holder.moveScoreOne.setText(mData.get(position).getFirstScoreText());
        holder.moveScoreTwo.setText(mData.get(position).getSecondScoreText());


    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView roundNum;
        EditText moveScoreOne;
        EditText moveScoreTwo;


        public MyViewHolder(View itemView) {
            super(itemView);

            roundNum = (TextView) itemView.findViewById(R.id.round_id);
            moveScoreOne = (EditText) itemView.findViewById(R.id.moveOne_id);
            moveScoreTwo = (EditText) itemView.findViewById(R.id.moveTwo_id);

        }

    }

}
