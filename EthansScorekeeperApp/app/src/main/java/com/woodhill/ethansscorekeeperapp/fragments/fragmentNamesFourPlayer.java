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
 * Use the {@link fragmentNamesFourPlayer#newInstance} factory method to
 * create an instance of this fragment.
 */
public class fragmentNamesFourPlayer extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    EditText playerOneName;
    EditText playerTwoName;
    EditText playerThreeName;
    EditText playerFourName;
    MainActivity main;

    public fragmentNamesFourPlayer() {
        // Required empty public constructor
    }

    public fragmentNamesFourPlayer(MainActivity main) {
        this.main = main;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment fragmentRVFourPlayer.
     */
    // TODO: Rename and change types and number of parameters
    public static fragmentNamesFourPlayer newInstance(String param1, String param2) {
        fragmentNamesFourPlayer fragment = new fragmentNamesFourPlayer();
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
        playerOneName = getView().findViewById(R.id.playerOneName);
        playerTwoName = getView().findViewById(R.id.playerTwoName);
        playerThreeName = getView().findViewById(R.id.playerThreeName);
        playerFourName = getView().findViewById(R.id.playerFourName);
        main.callSetPlayerNames();
    }

    public List<String> getPlayerNames () {
        List<String> nameList = new ArrayList<>();
        nameList.add(String.valueOf(playerOneName.getText()));
        nameList.add(String.valueOf(playerTwoName.getText()));
        nameList.add(String.valueOf(playerThreeName.getText()));
        nameList.add(String.valueOf(playerFourName.getText()));
        return nameList;
    }

    public void setPlayerNames (List<String> nameList) {
        playerOneName.setText(nameList.get(0));
        playerTwoName.setText(nameList.get(1));
        playerThreeName.setText(nameList.get(2));
        playerFourName.setText(nameList.get(3));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_rv_four_player, container, false);
    }
}