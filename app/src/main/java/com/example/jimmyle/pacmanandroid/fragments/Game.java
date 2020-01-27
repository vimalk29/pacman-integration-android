package com.example.jimmyle.pacmanandroid.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.jimmyle.pacmanandroid.R;
import com.example.jimmyle.pacmanandroid.gamefiles.activities.GameActivity;

public class Game extends Fragment implements View.OnClickListener {
    private Button playNowBtn;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_game, container, false);
        playNowBtn = view.findViewById(R.id.playNowButton);
        playNowBtn.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.playNowButton:
                startActivity(new Intent(getActivity(), GameActivity.class));
                break;
        }
    }
}
