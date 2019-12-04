package com.example.applicationtest.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.applicationtest.R;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;


    private TextView TwLoc;
    private TextView TWcodeur;
    private TextView TwLocps;
    private TextView Twcodeurps;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        this.TWcodeur = root.findViewById(R.id.CompteursC);
        this.TwLoc = root.findViewById(R.id.CompteursLOC);
        this.Twcodeurps = root.findViewById(R.id.CompteursCps);
        this.TwLocps = root.findViewById(R.id.CompteursLOCps);
        return root;
    }

    public void onCreate(){

    }

    public void refreshTW(double codeurs, double loc, double locps, double codeursps) {


        TWcodeur.setText((int) codeurs + "\nCodeurs");
        TwLoc.setText((int) loc + "\nLignes de code");
        TwLocps.setText(locps + "\nLOC / s");
        Twcodeurps.setText(codeursps + "\nCodeurs / s");

    }


}