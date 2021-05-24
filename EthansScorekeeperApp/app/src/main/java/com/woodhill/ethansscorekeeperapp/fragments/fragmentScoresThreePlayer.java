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
 * Use the {@link fragmentScoresThreePlayer#newInstance} factory method to
 * create an instance of this fragment.
 */
public class fragmentScoresThreePlayer extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    TextView totalScoreOne;
    TextView totalScoreTwo;
    TextView totalScoreThree;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public fragmentScoresThreePlayer() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment fragmentScoresThreePlayer.
     */
    // TODO: Rename and change types and number of parameters
    public static fragmentScoresThreePlayer newInstance(String param1, String param2) {
        fragmentScoresThreePlayer fragment = new fragmentScoresThreePlayer();
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

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        totalScoreOne = getView().findViewById(R.id.totalScoreOne);
        totalScoreTwo = getView().findViewById(R.id.totalScoreTwo);
        totalScoreThree = getView().findViewById(R.id.totalScoreThree);
    }

    public void setScores(int scoreOne, int scoreTwo, int scoreThree) {
        try {
            totalScoreOne.setText(String.valueOf(scoreOne));
            totalScoreTwo.setText(String.valueOf(scoreTwo));
            totalScoreThree.setText(String.valueOf(scoreThree));
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_scores_three_player, container, false);
    }
}