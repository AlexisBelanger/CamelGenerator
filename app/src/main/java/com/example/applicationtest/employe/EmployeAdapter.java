package com.example.applicationtest.employe;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.applicationtest.GameState;
import com.example.applicationtest.R;

import java.util.ArrayList;

public class EmployeAdapter extends ArrayAdapter<Employe> {

    public static GameState gs;

    public EmployeAdapter(Context context, ArrayList<Employe> employes, GameState gs) {

        super(context, 0, employes);
        Log.i("gs adapter", gs + "");
        this.gs = gs;


    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Log.i("EMploye", "get wiew");


        // Get the data item for this position

        final Employe employe = getItem(position);

        Log.i("gs adapter", gs + "");
        employe.setGs(this.gs);

        // Check if an existing view is being reused, otherwise inflate the view

        if (convertView == null) {

            convertView = LayoutInflater.from(getContext()).inflate(R.layout.employe_layout, parent, false);

        }

        // Lookup view for data population

        TextView empName = (TextView) convertView.findViewById(R.id.empNom);

        TextView empNB = (TextView) convertView.findViewById(R.id.empNB);
        TextView empCost = (TextView) convertView.findViewById(R.id.empCost);
        TextView empRate = (TextView) convertView.findViewById(R.id.empRate);
        Button empBut = (Button) convertView.findViewById(R.id.empBut);

        // Populate the data into the template view using the data object

        empName.setText(employe.nom);
        empCost.setText(employe.cout + " LOC");
        empNB.setText(employe.nb + "");
        empRate.setText(employe.rate + " LOC/s");


        empBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.i("gs adapter", gs + "");
                employe.setGs(gs);
                Log.i("employe", "ADD ONE");
                employe.addOne();
            }
        });

        // Return the completed view to render on screen

        return convertView;

    }


}
