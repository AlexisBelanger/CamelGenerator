package com.example.applicationtest.employe;

import android.app.AlertDialog;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.applicationtest.AcceuilActivity;
import com.example.applicationtest.GameState;
import com.example.applicationtest.R;
import com.example.applicationtest.utils;

import java.util.ArrayList;

public class EmployeAdapter extends ArrayAdapter<Employe> {


    public EmployeAdapter(Context context, ArrayList<Employe> employes) {

        super(context, 0, employes);

    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // Get the data item for this position

        final Employe employe = getItem(position);


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

        final ImageButton empInfo = (ImageButton) convertView.findViewById(R.id.empInfo);

        // Populate the data into the template view using the data object

        Employe e = ((AcceuilActivity) getContext()).getGameState().employes.get(employe.id);

        empName.setText(e.nom);
        empCost.setText(utils.prettyfier((int) e.cout) + " LOC");
        empNB.setText(utils.prettyfier(e.nb));
        empRate.setText(utils.prettyfier(e.total_rate) + " LOC/s");

        final ImageView im = (ImageView) convertView.findViewById(R.id.empImage);
        int id = getContext().getResources().getIdentifier(employe.getSrc_image(), "drawable", getContext().getPackageName());

        im.setImageResource(id);


        //boutton achat

        empBut.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                GameState gs = ((AcceuilActivity) getContext()).getGameState();
                Employe e = gs.employes.get(employe.id);
                if (gs.loc >= e.getCout()) {


                    e.addOne(((AcceuilActivity) getContext()).getGameState());
                    empCost.setText(utils.prettyfier(e.cout) + " LOC");
                    empNB.setText(utils.prettyfier(e.nb));
                    empRate.setText(utils.prettyfier(e.total_rate) + " LOC/s");
                    ((AcceuilActivity) getContext()).updateText();
                } else {
                    Toast.makeText(getContext(), "You don't have enough LOC for that!\nCome back later.", Toast.LENGTH_LONG).show();
                }
            }


        });


        empInfo.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

                builder.setMessage(employe.description).setTitle(employe.nom);

                AlertDialog dialog = builder.create();

                dialog.show();
            }


        });

        return convertView;

    }


}
