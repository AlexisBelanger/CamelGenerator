package com.example.applicationtest.ui.gallery;

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
import com.example.applicationtest.Amelioration.Balkany;
import com.example.applicationtest.Amelioration.CodeMaster;
import com.example.applicationtest.Amelioration.Fortnite;
import com.example.applicationtest.Amelioration.Nord;
import com.example.applicationtest.GameState;
import com.example.applicationtest.R;

public class GalleryFragment extends Fragment {


    private GalleryViewModel galleryViewModel;

    private View root;




    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {




        galleryViewModel =
                ViewModelProviders.of(this).get(GalleryViewModel.class);


        this.root = inflater.inflate(R.layout.fragment_lab, container, false);


        // Pour ne pas perdre l'affichage quand on change de fragment
        refreshCoderMasters();


        Button b1 = root.findViewById(R.id.button12);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                GameState gs = ((AcceuilActivity) getActivity()).getGameState();
                gs.addAmelioration(new CodeMaster());
                refreshCoderMasters();
            }
        });

        Button b2 = root.findViewById(R.id.button22);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GameState gs = ((AcceuilActivity) getActivity()).getGameState();
                gs.addAmelioration(new Fortnite());
                refreshCoderMasters();
            }
        });

        Button b3 = root.findViewById(R.id.button32);
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GameState gs = ((AcceuilActivity) getActivity()).getGameState();
                gs.addAmelioration(new Nord());
                refreshCoderMasters();
            }
        });

        Button b4 = root.findViewById(R.id.button42);
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GameState gs = ((AcceuilActivity) getActivity()).getGameState();
                gs.addAmelioration(new Balkany());
                refreshCoderMasters();
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



        return root;


    }


    public void refreshCoderMasters(){
        if (getActivity() != null && getActivity() instanceof AcceuilActivity) {

            if (((AcceuilActivity) getActivity()).getGameState().ameliorationMap.containsKey(("CodeMaster"))) {

                int codermasters = ((AcceuilActivity) getActivity()).getGameState().ameliorationMap.get("CodeMaster").getNb_taken();
                TextView TWcodermasters = root.findViewById(R.id.text4);
                TWcodermasters.setText("Hire Emmanuel Chailloux and force him to code to produce Camels.\n\nYou hired " + codermasters + " Chailloux.\n\nEach Chailloux improves your production by 10 camels.\n\n\nCost : 100");
            } else {

                int codermasters = 0;
                TextView TWcodermasters = root.findViewById(R.id.text4);
                TWcodermasters.setText("Hire Emmanuel Chailloux and force him to code to produce Camels.\n\nYou hired " + codermasters + " Chailloux.\n\nEach Chailloux improves your production by 10 camels.\n\n\nCost : 100");

            }
        }
    }





}