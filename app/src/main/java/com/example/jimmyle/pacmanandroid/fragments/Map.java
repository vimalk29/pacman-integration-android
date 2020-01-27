package com.example.jimmyle.pacmanandroid.fragments;

import android.content.Context;
import android.content.res.ColorStateList;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jimmyle.pacmanandroid.R;

public class Map extends Fragment implements View.OnClickListener {

    private  TextView mapTv, gamesTv;
    boolean gameSelected;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_map, container, false);
        mapTv = view.findViewById(R.id.mapText);
        gamesTv = view.findViewById(R.id.gameText);
        gameSelected=false;
        mapTv.setOnClickListener(this);
        gamesTv.setOnClickListener(this);
        onClick(mapTv);
        return view;
    }

    private void openFragment(Fragment fragment){
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.replace(R.id.mapFragLayout, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.mapText:
            case R.id.gameText:
                if (!gameSelected) {
                    //Toast.makeText(getContext(), "Map Clicked", Toast.LENGTH_SHORT).show();
                    openFragment(new EventMap());
                    mapTv.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorPrimary)));
                    mapTv.setTextColor(getResources().getColor(R.color.white));
                    gamesTv.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.white)));
                    gamesTv.setTextColor(getResources().getColor(R.color.black));
                    gameSelected=true;
                }else {
                    //Toast.makeText(getContext(), "Game Clicked", Toast.LENGTH_SHORT).show();
                    openFragment(new Game());
                    gamesTv.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorPrimary)));
                    gamesTv.setTextColor(getResources().getColor(R.color.white));
                    mapTv.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.white)));
                    mapTv.setTextColor(getResources().getColor(R.color.black));
                    gameSelected=false;
                }
                break;

        }
    }
}
