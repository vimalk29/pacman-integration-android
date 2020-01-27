package com.example.jimmyle.pacmanandroid.gamefiles.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.jimmyle.pacmanandroid.R;
import com.example.jimmyle.pacmanandroid.gamefiles.GameConditions;

import java.io.IOException;

public class GameActivity extends AppCompatActivity implements View.OnClickListener {
    private static MediaPlayer player;
    private static final String TAG = "GameActivity";
    private Button newGameButton, settingBtn, helpBtn, resumeButton;
    private TextView wonTv, nextTv;
    int flag;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        newGameButton = findViewById(R.id.newGameButton);
        settingBtn = findViewById(R.id.showSettingBtn);
        helpBtn=findViewById(R.id.helpButton);
        resumeButton = findViewById(R.id.resumeButton);
        wonTv = findViewById(R.id.textView3);
        nextTv=findViewById(R.id.textView4);
        flag = getIntent().getIntExtra("flag",0);


        resumeButton.setVisibility(View.GONE);
        nextTv.setVisibility(View.GONE);
        wonTv.setVisibility(View.GONE);
        newGameButton.setText("New Game");

        if (flag==0) {
            //code for first time activity
            if (player == null)
                player = MediaPlayer.create(this, R.raw.pacman_song);

            if (!player.isPlaying()) {
                player.setVolume(100, 100);
                player.setLooping(true);
                player.start();
            }
        }else if (flag==1){
            //code for pause
            player.start();
            resumeButton.setVisibility(View.VISIBLE);

        }else if (flag==2){
            //code for failed
            player.start();
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

        }else {
            //code for completed
            player.start();
            nextTv.setVisibility(View.VISIBLE);
            wonTv.setVisibility(View.VISIBLE);
            newGameButton.setText("Next Level");

        }
        newGameButton.setOnClickListener(this);
        settingBtn.setOnClickListener(this);
        helpBtn.setOnClickListener(this);
        resumeButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.newGameButton:
                Intent playIntent = new Intent(this, PlayActivity.class);
                startActivity(playIntent);
                if(flag!=3)
                    GameConditions.resetCurrentScore();
                break;
            case R.id.showSettingBtn:
                Intent settingIntent = new Intent(this,SettingsActivity.class);
                startActivity(settingIntent);
                break;
            case R.id.helpButton:
                Intent helpIntent = new Intent(this, HelpActivity.class);
                startActivity(helpIntent);
                break;
            case R.id.resumeButton:
                Intent resumeIntent = new Intent(this, PlayActivity.class);
                resumeIntent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(resumeIntent);
                break;
        }
    }

    public void toggleMusic(View view){
        if(player.isPlaying()){ player.stop(); }
        else{
            try {
                player.prepare();
            }
            catch(IOException ex){
                Log.d(TAG,"Prepare failed");
            }
            finally {
                player.start();
            }
        }
    }
    public static MediaPlayer getPlayer() {
        return player;
    }

    @Override
    public void onPause() {
        super.onPause();
        player.pause();
    }

    @Override
    public void onResume() {
        Log.i("info", "GameActivity onResume");
        super.onResume();
        player.start();
    }

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
