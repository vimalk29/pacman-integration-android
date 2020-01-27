package com.example.jimmyle.pacmanandroid.gamefiles.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.jimmyle.pacmanandroid.R;

/**
 * Created by colerogers on 7/10/16.
 */
public class FailedLevelActivity extends Activity {



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.failed_layout);
        GameActivity.getPlayer().start();

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.gameOver).setNeutralButton(R.string.ok, new AlertDialog.OnClickListener(){
            public void onClick(DialogInterface dialog, int which){
//                Intent failedIntent = new Intent(PlayActivity.getInstance(), GameActivity.class);
//                PlayActivity.getInstance().startActivity(failedIntent);
//                finish();

            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
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
    }

    public void showSetting(View view){
        Intent settingIntent = new Intent(this,SettingsActivity.class);
        startActivity(settingIntent);
    }
}
