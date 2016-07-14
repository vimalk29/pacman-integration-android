package com.example.jimmyle.pacmanandroid;

import android.app.Activity;
import android.os.Bundle;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import java.io.IOException;


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

    private static final String TAG = "SettingsActivity";
    public void playMusic(View view){
        if(MainActivity.getPlayer().isPlaying()){ MainActivity.getPlayer().stop(); }
        else{
            try {
                MainActivity.getPlayer().prepare();
            }
            catch(IOException ex){
                Log.d(TAG,"Prepare failed");
            }
            finally {
                MainActivity.getPlayer().start();
                
            }
        }
    }
}
