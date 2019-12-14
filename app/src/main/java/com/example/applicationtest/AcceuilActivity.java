package com.example.applicationtest;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.Menu;
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

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_cave, R.id.nav_lab,
                R.id.nav_test, R.id.nav_share, R.id.nav_send)
                .setDrawerLayout(drawer)
                .build();

        NavController navController = Navigation.findNavController(this, R.id.home_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        if (savedInstanceState != null) {

            gameState = new GameState(savedInstanceState.getString("gameState"));
        } else {
            gameState = new GameState();
        }


        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                gameState.secondTick();
            }
        }, 0, 1000);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.acceuil, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.home_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);

        savedInstanceState.putString("gameState", gameState.toJSON());

    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        gameState = new GameState(savedInstanceState.getString("gameState"));


    }

    @Override
    protected void onStop() {
        super.onStop();
        utils.writeTofile(gameState.toJSON(), "save.json", this);


    }


    @Override
    protected void onStart() {
        super.onStart();

        gameState = new GameState(utils.ReadFromfile("save.json", this));
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
                    idls * gameState.locps + " LOC\n").setTitle("Bon Retour Parmis Nous");

            AlertDialog dialog = builder.create();

            dialog.show();
        }
    }

    public GameState getGameState() {
        return gameState;
    }

    public void resetGameState() {
        gameState = new GameState();
    }
}
