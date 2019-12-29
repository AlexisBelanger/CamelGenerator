package com.example.applicationtest;

import android.app.AlertDialog;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.ui.AppBarConfiguration;

import com.example.applicationtest.ui.home.HomeFragment;
import com.example.applicationtest.ui.lab.LabFragment;
import com.example.applicationtest.ui.send.CaveFragment;
import com.example.applicationtest.ui.stats.StatFragment;
import com.example.applicationtest.ui.tavern.TavernFragment;
import com.example.applicationtest.ui.test.TestFragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class AcceuilActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;

    private GameState gameState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acceuil);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (savedInstanceState != null) {

            gameState = new GameState(this, savedInstanceState.getString("gameState"));
        } else {
            gameState = new GameState(this);
        }

        goTavern();

        findViewById(R.id.accClickbutton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gameState.click();
                gameState.updateValues();
                updateText();
            }
        });

        findViewById(R.id.goTavern).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goTavern();
            }
        });
        findViewById(R.id.goCave).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goCave();
            }
        });
        findViewById(R.id.goTest).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goTest();
            }
        });


        findViewById(R.id.goStat).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goStat();
            }
        });


        findViewById(R.id.goHome).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goHome();
            }
        });

        findViewById(R.id.goLab).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goLab();
            }
        });
        updateText();


        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                gameState.secondTick();
                updateText();
            }
        }, 0, 1000);

    }


    public void updateText() {
        runOnUiThread(new Runnable() {

            @Override
            public void run() {

                TextView loc = findViewById(R.id.accLOC);
                TextView locps = findViewById(R.id.accLOCps);

                loc.setText(utils.prettyfier(gameState.loc));
                locps.setText(utils.prettyfier(gameState.locps));

            }
        });
    }

    public void goTavern() {

        if (getSupportFragmentManager().findFragmentById(R.id.fragment_tavern) == null) {

            // Create new fragment and transaction
            Fragment newFragment = new TavernFragment();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

// Replace whatever is in the fragment_container view with this fragment,
// and add the transaction to the back stack if needed
            transaction.replace(R.id.fragment_container, newFragment);
            transaction.addToBackStack(null);

// Commit the transaction
            transaction.commit();


        }

    }

    public void goLab() {
        if (getSupportFragmentManager().findFragmentById(R.id.fragment_lab) == null) {

            // Create new fragment and transaction
            Fragment newFragment = new LabFragment();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

// Replace whatever is in the fragment_container view with this fragment,
// and add the transaction to the back stack if needed
            transaction.replace(R.id.fragment_container, newFragment);
            transaction.addToBackStack(null);

// Commit the transaction
            transaction.commit();


        }


    }

    public void goCave() {
        if (getSupportFragmentManager().findFragmentById(R.id.fragment_cave) == null) {

            // Create new fragment and transaction
            Fragment newFragment = new CaveFragment();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

// Replace whatever is in the fragment_container view with this fragment,
// and add the transaction to the back stack if needed
            transaction.replace(R.id.fragment_container, newFragment);
            transaction.addToBackStack(null);

// Commit the transaction
            transaction.commit();


        }


    }


    public void goHome() {
        if (getSupportFragmentManager().findFragmentById(R.id.fragment_home) == null) {

            // Create new fragment and transaction
            Fragment newFragment = new HomeFragment();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

// Replace whatever is in the fragment_container view with this fragment,
// and add the transaction to the back stack if needed
            transaction.replace(R.id.fragment_container, newFragment);
            transaction.addToBackStack(null);

// Commit the transaction
            transaction.commit();


        }


    }


    public void goTest() {
        if (getSupportFragmentManager().findFragmentById(R.id.fragment_test) == null) {

            // Create new fragment and transaction
            Fragment newFragment = new TestFragment();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

// Replace whatever is in the fragment_container view with this fragment,
// and add the transaction to the back stack if needed
            transaction.replace(R.id.fragment_container, newFragment);
            transaction.addToBackStack(null);

// Commit the transaction
            transaction.commit();


        }

    }

    public void goStat() {
        if (getSupportFragmentManager().findFragmentById(R.id.fragment_stat) == null) {

            // Create new fragment and transaction
            Fragment newFragment = new StatFragment();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

// Replace whatever is in the fragment_container view with this fragment,
// and add the transaction to the back stack if needed
            transaction.replace(R.id.fragment_container, newFragment);
            transaction.addToBackStack(null);

// Commit the transaction
            transaction.commit();


        }


    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.acceuil, menu);
        return true;
    }


    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);

        savedInstanceState.putString("gameState", gameState.toJSON());

    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        gameState = new GameState(this, savedInstanceState.getString("gameState"));


    }

    @Override
    protected void onStop() {
        super.onStop();
        utils.writeTofile(gameState.toJSON(), "save.json", this);


    }


    @Override
    protected void onStart() {
        super.onStart();

        gameState = new GameState(this, utils.ReadFromfile("save.json", this));
        final String PREFS_NAME = "MyPrefsFile";

        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);


        if (settings.getBoolean("my_first_time", true)) {
            //the app is being launched for first time, do something
            Log.d("Comments", "First time");

            // record the fact that the app has been started at least once
            settings.edit().putBoolean("my_first_time", false).commit();
        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);

            Long idls = gameState.idleSeconds;
            Log.i("idsl activity", idls + "");
            builder.setMessage("Pendant votre Absence la corporation a géneré :\n" +
                    utils.prettyfier(idls * gameState.locps) + " LOC\n").setTitle("Bon Retour Parmis Nous");

            AlertDialog dialog = builder.create();

            dialog.show();
        }
    }

    public GameState getGameState() {
        return gameState;
    }

    public void resetGameState() {
        gameState = new GameState(this);
    }
}
