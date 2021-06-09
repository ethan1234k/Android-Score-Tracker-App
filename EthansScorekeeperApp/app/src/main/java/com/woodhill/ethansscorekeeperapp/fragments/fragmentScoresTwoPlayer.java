package com.woodhill.ethansscorekeeperapp.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.woodhill.ethansscorekeeperapp.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link fragmentScoresTwoPlayer#newInstance} factory method to
 * create an instance of this fragment.
 */
public class fragmentScoresTwoPlayer extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    TextView totalScoreOne;
    TextView totalScoreTwo;

    private String mParam1;
    private String mParam2;

    public fragmentScoresTwoPlayer() {
        // Required empty public constructor
    }

    public static fragmentScoresTwoPlayer newInstance(String param1, String param2) {
        fragmentScoresTwoPlayer fragment = new fragmentScoresTwoPlayer();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    //Sets text fields for scores
    public void onViewCreated(View view, Bundle savedInstanceState) {
        totalScoreOne = getView().findViewById(R.id.totalScoreOne);
        totalScoreTwo = getView().findViewById(R.id.totalScoreTwo);
    }

    //Takes in scores and sets the text fields to those scores
    public void setScores(int scoreOne, int scoreTwo) {
        try {
            totalScoreOne.setText(String.valueOf(scoreOne));
            totalScoreTwo.setText(String.valueOf(scoreTwo));
        } catch(NullPointerException e) {
            e.printStackTrace();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_scores_two_player, container, false);
    }
}