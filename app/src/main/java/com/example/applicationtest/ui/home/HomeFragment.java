package com.example.applicationtest.ui.home;

import android.app.AlertDialog;
import android.content.DialogInterface;
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
import com.example.applicationtest.GameState;
import com.example.applicationtest.R;

import java.util.Timer;
import java.util.TimerTask;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;

    private View root;

    private GameState gameState;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        this.root = inflater.inflate(R.layout.fragment_home, container, false);



        Button click = root.findViewById(R.id.Clickbutton);

        click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("Test", "click home");
                ((AcceuilActivity) getActivity()).getGameState().click();
                refreshTW();
            }
        });



        Button reset = root.findViewById(R.id.Resetbutton);
        reset.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                System.out.println("ET MERDE");
                AlertDialog.Builder builder=new AlertDialog.Builder(getContext());
                builder.setTitle("Confirmation");
                builder.setMessage("Etes vous sûr de vouloir repartir de zéro ?");
                System.out.println("JE SUIS ITALIEN");
                builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        ((AcceuilActivity) getActivity()).getGameState().resetArmy();

                    }
                });

                builder.setNeutralButton(android.R.string.cancel, null);
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        }
        );

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


    public void refreshTW() {

        if (getActivity() != null && getActivity() instanceof AcceuilActivity) {

            double locps = ((AcceuilActivity) getActivity()).getGameState().locps;
            double loc = ((AcceuilActivity) getActivity()).getGameState().loc;
            double codeurs = ((AcceuilActivity) getActivity()).getGameState().codeurs;
            double codeursps = ((AcceuilActivity) getActivity()).getGameState().codeursps;

            TextView TWcodeur = root.findViewById(R.id.CompteursC);
            TextView TwLoc = root.findViewById(R.id.CompteursLOC);
            TextView Twcodeurps = root.findViewById(R.id.CompteursCps);
            TextView TwLocps = root.findViewById(R.id.CompteursLOCps);


            TWcodeur.setText((int) codeurs + "\nCodeurs");
            TwLoc.setText((int) loc + "\nLignes de code");
            TwLocps.setText(locps + "\nLOC / s");
            Twcodeurps.setText(codeursps + "\nCodeurs / s");
        }
    }


}