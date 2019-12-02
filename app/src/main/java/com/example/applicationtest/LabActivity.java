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

public class LabActivity extends AppCompatActivity {


    private int camels;
    private int chailloux;
    private int fortniteWC;
    private int nordvpnOP;
    private int patoche;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab);

        camels = 1;
        chailloux = 0;
        fortniteWC = 0;
        nordvpnOP = 0;
        patoche = 0 ;

        final TextView emmanuel = findViewById(R.id.text4);
        final TextView fortnite = findViewById(R.id.text3);
        final TextView nordvpn = findViewById(R.id.text2);
        final TextView patrick = findViewById(R.id.text1);

        final Intent i = new Intent(this,MainActivity.class);
        SharedPreferences pref = getPreferences(MODE_PRIVATE);

        camels = pref.getInt("camels",1);
        chailloux = pref.getInt("chailloux",0);
        fortniteWC = pref.getInt("WC",0);
        nordvpnOP = pref.getInt("vpn",0);
        patoche = pref.getInt("PB",0);

        emmanuel.setText("Hire Emmanuel Chailloux and force him to code to produce Camels.\n\nYou hired "+chailloux+" Chailloux.\n\nEach Chailloux improves your production by 10 camels.\n\n\nCost : 100");
        fortnite.setText("Participate in the Fortnite World Cup and win the prize pool.\n\nYou won "+fortniteWC+" World Cups.\n\nEach World you win gives you 700 camels.\n\n\n\nCost : 5 000");
        nordvpn.setText("Post a video sponsored by NordVPN on your Youtube channel, they will reward you with camels.\n\nYou posted "+nordvpnOP+" sponsored videos.\n\nEach video will give you \n2 500 camels.\n\n\nCost : 75 000");
        patrick.setText("Patrick Balkany will steal Levallois Perret people's camels to give it to you.\n\n\nYou have "+patoche+" Patrick Balkany.\n\nEach Patrick Balkany will steal 10 000 camels.\n\n\nCost : 750 000");




        Button b1 = findViewById(R.id.button12);
        b1.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Log.i("Test", "click");
                chailloux++;
                camels = camels+10;
                emmanuel.setText("Hire Emmanuel Chailloux and force him to code to produce Camels.\n\nYou hired "+chailloux+" Chailloux.\n\nEach Chailloux improves your production by 10 camels.\n\n\nCost : 100");
                i.putExtra("camels", camels);
            }
        });

        Button b2 = findViewById(R.id.button22);
        b2.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Log.i("Test", "click");
                fortniteWC++;
                camels = camels + 700;
                fortnite.setText("Participate in the Fortnite World Cup and win the prize pool.\n\nYou won "+fortniteWC+" World Cups.\n\nEach World you win gives you 700 camels.\n\n\n\nCost : 5 000");
                i.putExtra("camels", camels);
            }
        });

        Button b3 = findViewById(R.id.button32);
        b3.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Log.i("Test", "click");
                nordvpnOP++;
                camels = camels + 2500;
                nordvpn.setText("Post a video sponsored by NordVPN on your Youtube channel, they will reward you with camels.\n\nYou posted "+nordvpnOP+" sponsored videos.\n\nEach video will give you \n2 500 camels.\n\n\nCost : 75 000");
                i.putExtra("camels", camels);
            }
        });

        Button b4 = findViewById(R.id.button42);
        b4.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Log.i("Test", "click");
                patoche++;
                camels = camels + 10000;
                patrick.setText("Patrick Balkany will steal Levallois Perret people's camels to give it to you.\n\n\nYou have "+patoche+" Patrick Balkany.\n\nEach Patrick Balkany will steal 10 000 camels.\n\n\nCost : 750 000");
                i.putExtra("camels", camels);
            }
        });


        SharedPreferences.Editor editor = pref.edit();
        editor.putInt("camels",camels);
        editor.putInt("chailloux",chailloux);
        editor.putInt("WC", fortniteWC);
        editor.putInt("vpn", nordvpnOP);
        editor.putInt("PB",patoche);

        Button b5 = findViewById(R.id.button5);
        b5.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Log.i("Test", "click");
                startActivity(i);
            }
        });


        editor.apply();





    }



}
