package com.example.applicationtest;

import android.content.Intent;
import android.os.Bundle;

import com.example.applicationtest.ui.home.HomeFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.util.Log;
import android.view.View;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class AcceuilActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;

    //test TODO a rendre propre
    public double codeurs = 0;
    public double loc = 0;
    public double locps = 0;
    public double codeursps = 0;

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
                Log.i("var loc", loc + "");
                Log.i("var cod", codeurs + "");

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



    public void moveCave(View view) {

        //cave a creer
        Intent i = new Intent(this, AcceuilActivity.class);
        startActivity(i);

    }



}
