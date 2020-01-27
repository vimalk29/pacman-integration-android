package com.example.jimmyle.pacmanandroid.gamefiles.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.jimmyle.pacmanandroid.R;

import java.io.IOException;

/**
 * Created by colerogers on 7/9/16.
 */
public class CompletedLevelActivity extends Activity {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //sets up the view of the completed game screen
        setContentView(R.layout.completed_layout);
        GameActivity.getPlayer().start();
    }

    // Method to start activity for Help button
    public void showHelpScreen(View view) {
        Intent helpIntent = new Intent(this, HelpActivity.class);
        startActivity(helpIntent);
    }

    //Method to start activity for Settings button
    public void showSettingsScreen(View view){
        Intent settingIntent = new Intent(this,SettingsActivity.class);
        startActivity(settingIntent);
    }

    // Method to start activity for Play button
    public void showPlayScreen(View view) {
        Intent playIntent = new Intent(this, PlayActivity.class);
        startActivity(playIntent);
        PlayActivity.getInstance().finish();
        this.finish();
    }

    private static final String TAG = "SettingsActivity";

    public void playMusic(View view){
        if(GameActivity.getPlayer().isPlaying()){
            GameActivity.getPlayer().stop();
        }
        else{
            try {
                GameActivity.getPlayer().prepare();
            }
            catch(IOException ex){
                Log.d(TAG,"Prepare failed");
            }
            finally {
                GameActivity.getPlayer().start();
            }
        }
    }
}
