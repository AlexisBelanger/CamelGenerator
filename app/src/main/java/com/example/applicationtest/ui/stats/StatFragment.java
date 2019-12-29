package com.example.applicationtest.ui.stats;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.applicationtest.AcceuilActivity;
import com.example.applicationtest.R;
import com.example.applicationtest.utils;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

public class StatFragment extends Fragment {


    private View root;

    private StatViewModel testViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        testViewModel =
                ViewModelProviders.of(this).get(StatViewModel.class);
        root = inflater.inflate(R.layout.fragment_stat, container, false);

        runLOCGraph();
        return root;
    }





    public void runLOCGraph(){

        LineGraphSeries<DataPoint> points = ((AcceuilActivity)getActivity()).getGameState().getPoints();


        GraphView gv = root.findViewById(R.id.loc_graph);
        gv.addSeries(points);


        gv.getViewport().setMaxX(((AcceuilActivity)getActivity()).getGameState().getMax_Xdisplay());
        //gv.getViewport().setMinY(0.0);
        gv.getViewport().setMaxXAxisSize(10);
        gv.getViewport().setYAxisBoundsManual(true);
        gv.getViewport().setXAxisBoundsManual(true);

    }



}