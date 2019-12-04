package com.example.applicationtest.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.applicationtest.AcceuilActivity;
import com.example.applicationtest.R;

import java.util.Timer;
import java.util.TimerTask;

public class HomeFragment extends Fragment implements View.OnClickListener {

    private HomeViewModel homeViewModel;


    private TextView TwLoc;
    private TextView TWcodeur;
    private TextView TwLocps;
    private TextView Twcodeurps;

    private double codeurs = 0;
    private double loc = 0;
    private double locps = 0;
    private double codeursps = 0;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        this.TWcodeur = root.findViewById(R.id.CompteursC);
        this.TwLoc = root.findViewById(R.id.CompteursLOC);
        this.Twcodeurps = root.findViewById(R.id.CompteursCps);
        this.TwLocps = root.findViewById(R.id.CompteursLOCps);

        Button click = root.findViewById(R.id.Clickbutton);
        Log.i("Test", "button :" + click);

        click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("Test", "click home");
                ((AcceuilActivity) getActivity()).codeurs++;
                refreshTW();
            }
        });

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                refreshTW();
            }
        }, 0, 1000);

        return root;
    }

    public void onCreate(){

    }

    public void onClick(View v) {
        //do what you want to do when button is clicked
        Log.i("event", "onClick called ");
        switch (v.getId()) {
            case R.id.Clickbutton:
                ((AcceuilActivity) getActivity()).codeurs++;
                refreshTW();
        }
    }

    public void refreshTW() {

        if (getActivity() != null && getActivity() instanceof AcceuilActivity) {

            this.locps = ((AcceuilActivity) getActivity()).locps;
            this.loc = ((AcceuilActivity) getActivity()).loc;
            this.codeurs = ((AcceuilActivity) getActivity()).codeurs;
            this.codeursps = ((AcceuilActivity) getActivity()).codeursps;

            TWcodeur.setText((int) codeurs + "\nCodeurs");
            TwLoc.setText((int) loc + "\nLignes de code");
            TwLocps.setText(locps + "\nLOC / s");
            Twcodeurps.setText(codeursps + "\nCodeurs / s");
        }
    }


}