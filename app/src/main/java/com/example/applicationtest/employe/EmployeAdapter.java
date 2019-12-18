package com.example.applicationtest.employe;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.applicationtest.AcceuilActivity;
import com.example.applicationtest.R;

import java.util.ArrayList;

public class EmployeAdapter extends ArrayAdapter<Employe> {


    public EmployeAdapter(Context context, ArrayList<Employe> employes) {

        super(context, 0, employes);

    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // Get the data item for this position

        Employe employe = getItem(position);

        Log.i("Loading", "nb: " + employe.getNb());

        // Check if an existing view is being reused, otherwise inflate the view

        if (convertView == null) {

            convertView = LayoutInflater.from(getContext()).inflate(R.layout.employe_layout, parent, false);

        }

        // Lookup view for data population

        final TextView empName = (TextView) convertView.findViewById(R.id.empNom);

        final TextView empNB = (TextView) convertView.findViewById(R.id.empNB);
        final TextView empCost = (TextView) convertView.findViewById(R.id.empCost);
        final TextView empRate = (TextView) convertView.findViewById(R.id.empRate);
        final Button empBut = (Button) convertView.findViewById(R.id.empBut);

        // Populate the data into the template view using the data object

//Il faut remettre la valeur ,je sais pas pourquoi
        //TODO trouver la raison, dupliquation d'instance possible
        for (Employe e : ((AcceuilActivity) getContext()).getGameState().employes.values()) {
            if (e.getNom().equals(empName.getText())) {
                employe.setNb(e.getNb());
            }
        }


        Log.i("Loading", "nb: " + employe.getNb());

        empBut.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Employe e = ((AcceuilActivity) getContext()).getGameState().employes.get(employe.id);
                Log.i("Employe adapter", "employe nom " + employe.getNom());
                Log.i("Employe adapter", "e nom " + e.getNom());


                e.addOne(((AcceuilActivity) getContext()).getGameState());
                empCost.setText(((int) e.cout) + " LOC");
                empNB.setText(e.nb + "");
                empRate.setText(e.rate + " LOC/s");
                ((AcceuilActivity) getContext()).updateText();

            }
        });

        empName.setText(employe.nom);
        empCost.setText(((int) employe.cout) + " LOC");
        empNB.setText(employe.nb + "");
        empRate.setText(employe.rate + " LOC/s");


        return convertView;

    }


}
