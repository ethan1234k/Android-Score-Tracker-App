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
 * Use the {@link fragmentScoresFourPlayer#newInstance} factory method to
 * create an instance of this fragment.
 */
public class fragmentScoresFourPlayer extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    TextView totalScoreOne;
    TextView totalScoreTwo;
    TextView totalScoreThree;
    TextView totalScoreFour;

    private String mParam1;
    private String mParam2;

    public fragmentScoresFourPlayer() {
        // Required empty public constructor
    }

    public static fragmentScoresFourPlayer newInstance(String param1, String param2) {
        fragmentScoresFourPlayer fragment = new fragmentScoresFourPlayer();
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

    //Connects the text fields
    public void onViewCreated(View view, Bundle savedInstanceState) {
        totalScoreOne = getView().findViewById(R.id.totalScoreOne);
        totalScoreTwo = getView().findViewById(R.id.totalScoreTwo);
        totalScoreThree = getView().findViewById(R.id.totalScoreThree);
        totalScoreFour = getView().findViewById(R.id.totalScoreFour);
    }

    //Takes in the scores and sets the texts to the scores
    public void setScores(int scoreOne, int scoreTwo, int scoreThree, int scoreFour) {
        try {
            totalScoreOne.setText(String.valueOf(scoreOne));
            totalScoreTwo.setText(String.valueOf(scoreTwo));
            totalScoreThree.setText(String.valueOf(scoreThree));
            totalScoreFour.setText(String.valueOf(scoreFour));
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_scores_four_player, container, false);
    }
}