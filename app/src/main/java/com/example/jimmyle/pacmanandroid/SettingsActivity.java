package com.example.jimmyle.pacmanandroid;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created by Kevin on 7/13/16.
 */

public class SettingsActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_layout);
        MainActivity.getPlayer().start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        MainActivity.getPlayer().pause();
    }
}
