package com.example.applicationtest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private int camels;
    private int chailloux;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        camels = 0;
        chailloux = 0;

        SharedPreferences pref = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt("camels",camels);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView count = findViewById(R.id.text1);
        final TextView emmanuels = findViewById(R.id.text2);

        final MediaPlayer mp = MediaPlayer.create(this, R.raw.test);

        Button b1 = findViewById(R.id.button1);
        b1.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = getIntent();
                Log.i("Test", "click");
                SharedPreferences pref = getPreferences(MODE_PRIVATE);
                SharedPreferences.Editor editor = pref.edit();
                editor.putInt("camels",intent.getIntExtra("camels", 1));
                camels += pref.getInt("camels", 1);
                if(mp.isPlaying())

                    mp.seekTo(0L, MediaPlayer.SEEK_NEXT_SYNC); // continues playback from millisecond 0
                else
                    mp.start();
                count.setTextSize(50);
                count.setText("You have\n"+camels+"\ncamels !");

            }
        });
        final Intent i = new Intent(this,LabActivity.class);
        Button b2 = findViewById(R.id.button2);
        b2.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View view){


                startActivity(i);
                Log.i("Test", "click");
                chailloux ++;
                emmanuels.setText("Number of Chailloux : "+chailloux);
            }
        });

        Button b3 = findViewById(R.id.button3);
        b3.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Log.i("Test", "click");
                chailloux=0;
                camels = 0;
                emmanuels.setText("Number of Chailloux : 0");
                count.setText("No camels");
            }
        });
    }


}
