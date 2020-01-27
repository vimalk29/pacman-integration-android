package com.example.jimmyle.pacmanandroid.gamefiles.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.jimmyle.pacmanandroid.R;
import com.example.jimmyle.pacmanandroid.gamefiles.GameConditions;

public class PauseActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.paused_layout);
        GameActivity.getPlayer().start();
    }

    // Method to start activity for Help button
    public void showHelpScreen(View view) {
        Intent helpIntent = new Intent(this, HelpActivity.class);
        startActivity(helpIntent);
    }

    // Method to start activity for Play button
    public void showPlayScreen(View view) {
        Intent playIntent = new Intent(this, PlayActivity.class);
        startActivity(playIntent);
        PlayActivity.getInstance().finish();
        this.finish();
        GameConditions.resetCurrentScore();
    }

    // Method to resume the game
    public void resumeGame(View view) {
        Intent resumeIntent = new Intent(this, PlayActivity.class);
        resumeIntent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(resumeIntent);
        this.finish();
    }
    public void showSetting(View view){
        Intent settingIntent = new Intent(this,SettingsActivity.class);
        startActivity(settingIntent);
    }


}
