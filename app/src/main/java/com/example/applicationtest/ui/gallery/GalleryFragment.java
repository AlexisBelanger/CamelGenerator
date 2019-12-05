package com.example.applicationtest.ui.gallery;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.applicationtest.AcceuilActivity;
import com.example.applicationtest.GameState;
import com.example.applicationtest.LabActivity;
import com.example.applicationtest.MainActivity;
import com.example.applicationtest.R;
import com.example.applicationtest.ui.home.HomeViewModel;

import java.util.Timer;
import java.util.TimerTask;

public class GalleryFragment extends Fragment {


    private GalleryViewModel galleryViewModel;

    private View root;




    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {




        galleryViewModel =
                ViewModelProviders.of(this).get(GalleryViewModel.class);


        this.root = inflater.inflate(R.layout.fragment_lab, container, false);


        // Pour ne pas perdre l'affichage quand on change de fragment
        refreshBALKANY();
        refreshCoderMasters();
        refreshFWC();
        refreshNVPN();


        Button b1 = root.findViewById(R.id.button12);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("Test", "click");
                ((AcceuilActivity) getActivity()).getGameState().addCoderMaster();
                refreshCoderMasters();
            }
        });

        Button b2 = root.findViewById(R.id.button22);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("Test", "click");
                ((AcceuilActivity) getActivity()).getGameState().addFWC();
                refreshFWC();
            }
        });

        Button b3 = root.findViewById(R.id.button32);
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("Test", "click");
                ((AcceuilActivity) getActivity()).getGameState().addOpeSpe();
                refreshNVPN();
            }
        });

        Button b4 = root.findViewById(R.id.button42);
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("Test", "click");
                ((AcceuilActivity) getActivity()).getGameState().addBALKANY();
                refreshBALKANY();
            }
        });


        Button b5 = root.findViewById(R.id.button5);
        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("Test", "click");
               // startActivity(i);
            }
        });


        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                //refresh
            }
        }, 0, 1000);

        return root;


    }


    public void refreshCoderMasters(){
        if (getActivity() != null && getActivity() instanceof AcceuilActivity) {
            double codermasters = ((AcceuilActivity) getActivity()).getGameState().coderMasters;
            TextView TWcodermasters = root.findViewById(R.id.text4);
            TWcodermasters.setText("Hire Emmanuel Chailloux and force him to code to produce Camels.\n\nYou hired " + codermasters + " Chailloux.\n\nEach Chailloux improves your production by 10 camels.\n\n\nCost : 100");
        }
    }

    public void refreshFWC(){
        if (getActivity() != null && getActivity() instanceof AcceuilActivity) {
            double FWC = ((AcceuilActivity) getActivity()).getGameState().fortniteWorldCups;
            System.out.println(FWC);
            TextView TWFWC = root.findViewById(R.id.text3);
            TWFWC.setText("Participate in the Fortnite World Cup and win the prize pool.\n\nYou won " + FWC + " World Cups.\n\nEach World you win gives you 700 camels.\n\n\n\nCost : 5 000");
        }
    }

    public void refreshNVPN(){
        if (getActivity() != null && getActivity() instanceof AcceuilActivity) {
            double NVPN = ((AcceuilActivity) getActivity()).getGameState().nordVPN;
            TextView TWNVPN = root.findViewById(R.id.text2);
            TWNVPN.setText("Post a video sponsored by NordVPN on your Youtube channel, they will reward you with camels.\n\nYou posted " + NVPN + " sponsored videos.\n\nEach video will give you \n2 500 camels.\n\n\nCost : 75 000");
        }
    }


    public void refreshBALKANY(){
        if (getActivity() != null && getActivity() instanceof AcceuilActivity) {
            double balkany = ((AcceuilActivity) getActivity()).getGameState().balkany;
            TextView TWBalkany = root.findViewById(R.id.text1);
            TWBalkany.setText("Patrick Balkany will steal Levallois Perret people's camels to give it to you.\n\n\nYou have " + balkany + " Patrick Balkany.\n\nEach Patrick Balkany will steal 10 000 camels.\n\n\nCost : 750 000");
        }
    }




}