package com.example.applicationtest.Amelioration;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.applicationtest.AcceuilActivity;
import com.example.applicationtest.GameState;
import com.example.applicationtest.R;
import com.example.applicationtest.employe.Employe;
import com.example.applicationtest.utils;

import java.util.ArrayList;

public class AmeliorationAdapter extends ArrayAdapter<Amelioration> {

    public AmeliorationAdapter(Context context, ArrayList<Amelioration> ameliorations) {

        super(context, 0, ameliorations);

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        // Get the data item for this position

        final Amelioration amelioration = getItem(position);


        // Check if an existing view is being reused, otherwise inflate the view

        if (convertView == null) {

            convertView = LayoutInflater.from(getContext()).inflate(R.layout.amelioration_layout, parent, false);

        }


        final TextView ammName = (TextView) convertView.findViewById(R.id.AmeliorationNom);
        ammName.setText(amelioration.getNom());

        final TextView ammDesc = (TextView) convertView.findViewById(R.id.AmeliorationDescr);
        ammDesc.setText(amelioration.getDescription());
        final Button ammBut = (Button) convertView.findViewById(R.id.ammButton);
        ammBut.setText(utils.prettyfier(amelioration.getCost()));

        ammBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GameState gs = ((AcceuilActivity) getContext()).getGameState();
                if (gs.loc >= amelioration.getCost()) {

                    amelioration.ChangeState(gs);
                    gs.loc -= amelioration.getCost();
                }

                gs.updateValues();
                gs.taken_ameliorations.add(amelioration.id);
                gs.ameliorations.remove(amelioration.id);
                AmeliorationAdapter.super.remove(amelioration);
                AmeliorationAdapter.super.notifyDataSetChanged();



            }
        });

        return convertView;

    }
}