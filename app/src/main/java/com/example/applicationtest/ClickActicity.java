package com.example.applicationtest;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Timer;
import java.util.TimerTask;

public class ClickActicity extends AppCompatActivity {

    private TextView TwLoc;
    private TextView TWcodeur;
    private TextView TwLocps;
    private TextView Twcodeurps;

    private double codeurs = 0;
    private double loc = 0;
    private double locps = 0;
    private double codeursps = 0;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        this.TWcodeur = findViewById(R.id.CompteursC);
        this.TwLoc = findViewById(R.id.CompteursLOC);
        this.Twcodeurps = findViewById(R.id.CompteursCps);
        this.TwLocps = findViewById(R.id.CompteursLOCps);

        if (savedInstanceState != null) {

            this.codeurs = savedInstanceState.getDouble("codeurs");
            this.loc = savedInstanceState.getDouble("loc");
            this.codeursps = savedInstanceState.getDouble("codeursps");
            this.locps = savedInstanceState.getDouble("locps");

        }

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                loc += codeurs * 1;
                refreshTW();
            }
        }, 0, 1000);

    }


    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        // Save UI state changes to the savedInstanceState.
        // This bundle will be passed to onCreate if the process is
        // killed and restarted.

        savedInstanceState.putDouble("codeurs", codeurs);
        savedInstanceState.putDouble("loc", loc);
        savedInstanceState.putDouble("codeursps", codeursps);
        savedInstanceState.putDouble("locps", locps);

    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        // Restore UI state from the savedInstanceState.
        // This bundle has also been passed to onCreate.

        this.codeurs = savedInstanceState.getDouble("codeurs");
        this.loc = savedInstanceState.getDouble("loc");
        this.codeursps = savedInstanceState.getDouble("codeursps");
        this.locps = savedInstanceState.getDouble("locps");

    }

    public void refreshTW() {
        TWcodeur.setText((int) codeurs + "\nCodeurs");
        TwLoc.setText((int) loc + "\nLignes de code");
        TwLocps.setText(locps + "\nLOC / s");
        Twcodeurps.setText(codeursps + "\nCodeurs / s");
    }

    public void cliquer(View view) {
        System.out.println("LOL");
        codeurs++;
        refreshTW();
    }

}
