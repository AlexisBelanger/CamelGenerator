package com.example.applicationtest.challenge;

import android.content.Context;
import android.content.Intent;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.applicationtest.AcceuilActivity;
import com.example.applicationtest.GameState;
import com.example.applicationtest.R;
import com.example.applicationtest.employe.Employe;
import com.example.applicationtest.ui.challenge.Ten_seconds_fragment;
import com.example.applicationtest.ui.home.HomeFragment;
import com.example.applicationtest.utils;

import java.util.ArrayList;

public class ChallengeAdapter extends ArrayAdapter<Challenge> {


    public ChallengeAdapter(Context context, ArrayList<Challenge> ameliorations) {

        super(context, 0, ameliorations);

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        // Get the data item for this position

        final Challenge challenge = getItem(position);


        // Check if an existing view is being reused, otherwise inflate the view

        if (convertView == null) {

            convertView = LayoutInflater.from(getContext()).inflate(R.layout.challenge_layout, parent, false);

        }


        final TextView challName = (TextView) convertView.findViewById(R.id.ChallengeNom);
        challName.setText(challenge.getNom());

        final TextView challDesc = (TextView) convertView.findViewById(R.id.ChallDescr);
        challDesc.setText(challenge.getDescription());
        final Button challBut = (Button) convertView.findViewById(R.id.challengeButton);
        challBut.setText("Jouer");

        challBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Ten_seconds_fragment newFragment = new Ten_seconds_fragment();
                newFragment.show(((AcceuilActivity) getContext()).getSupportFragmentManager(), null);


            }
        });

        return convertView;

    }


}