package com.example.jimmyle.pacmanandroid.gamefiles.activities;

import android.app.Activity;
import android.os.Bundle;

import com.example.jimmyle.pacmanandroid.R;

public class HelpActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.help_layout);
        GameActivity.getPlayer().start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        GameActivity.getPlayer().pause();
    }

}
