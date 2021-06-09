package com.woodhill.ethansscorekeeperapp.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.woodhill.ethansscorekeeperapp.MainActivity;
import com.woodhill.ethansscorekeeperapp.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link fragmentNamesThreePlayer#newInstance} factory method to
 * create an instance of this fragment.
 */
public class fragmentNamesThreePlayer extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    EditText playerOneName;
    EditText playerTwoName;
    EditText playerThreeName;
    MainActivity main;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public fragmentNamesThreePlayer() {
        // Required empty public constructor
    }

    public fragmentNamesThreePlayer(MainActivity main) {
        this.main = main;
    }

    public static fragmentNamesThreePlayer newInstance(String param1, String param2) {
        fragmentNamesThreePlayer fragment = new fragmentNamesThreePlayer();
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

    //Sets the play name edit texts when the view is created
    public void onViewCreated(View view, Bundle savedInstanceState) {
        playerOneName = getView().findViewById(R.id.playerOneName);
        playerTwoName = getView().findViewById(R.id.playerTwoName);
        playerThreeName = getView().findViewById(R.id.playerThreeName);
        main.callSetPlayerNames();
    }

    //Method that returns an ArrayList of the player names
    public List<String> getPlayerNames () {
        List<String> nameList = new ArrayList<>();
        nameList.add(String.valueOf(playerOneName.getText()));
        nameList.add(String.valueOf(playerTwoName.getText()));
        nameList.add(String.valueOf(playerThreeName.getText()));
        return nameList;
    }

    //Sets the edit texts to the player names specified array list
    public void setPlayerNames (List<String> nameList) {
        playerOneName = getView().findViewById(R.id.playerOneName);
        playerTwoName = getView().findViewById(R.id.playerTwoName);
        playerThreeName = getView().findViewById(R.id.playerThreeName);
        playerOneName.setText(nameList.get(0));
        playerTwoName.setText(nameList.get(1));
        playerThreeName.setText(nameList.get(2));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_rv_three_player, container, false);
    }
}