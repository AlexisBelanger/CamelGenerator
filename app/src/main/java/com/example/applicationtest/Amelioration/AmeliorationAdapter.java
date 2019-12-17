package com.example.applicationtest.Amelioration;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.example.applicationtest.R;
import com.example.applicationtest.employe.Employe;

import java.util.ArrayList;

public class AmeliorationAdapter extends ArrayAdapter<Amelioration> {

    public AmeliorationAdapter(Context context, ArrayList<Amelioration> ameliorations) {

        super(context, 0, ameliorations);

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        // Get the data item for this position

        Amelioration amelioration = getItem(position);


        // Check if an existing view is being reused, otherwise inflate the view

        if (convertView == null) {

            convertView = LayoutInflater.from(getContext()).inflate(R.layout.employe_layout, parent, false);

        }
        return convertView;


    }
}