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
import com.example.applicationtest.Amelioration.Amelioration;
import com.example.applicationtest.GameState;
import com.example.applicationtest.R;
import com.example.applicationtest.employe.Employe;
import com.example.applicationtest.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import lecho.lib.hellocharts.model.PieChartData;
import lecho.lib.hellocharts.model.SliceValue;
import lecho.lib.hellocharts.view.PieChartView;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;

    private View root;

    private boolean tout_a_zero = true;

    private GameState gameState;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        this.root = inflater.inflate(R.layout.fragment_home, container, false);

        show_effectif();
        show_rentabilite();
        show_Production_totale();

        return root;
    }

    public void show_effectif(){
        ArrayList<Employe> employes = new ArrayList<Employe>(((AcceuilActivity) getActivity()).getGameState().employes.values());
        List<SliceValue> values = new ArrayList<SliceValue>();

        PieChartView nb_employes = root.findViewById(R.id.nb_employe);

        nb_employes.setChartRotationEnabled(false);

        int i = 0;

        if(tout_a_zero){
            for(Employe emp : employes){
                if(!(emp.getNb()==0)){
                    tout_a_zero = false;
                }
            }
        }
        for(Employe emp : employes){
            int val = 1;
            if(!tout_a_zero){
                val = emp.getNb();
            }
            SliceValue v = new SliceValue(val,((AcceuilActivity) getActivity()).getGameState().colors[i] );
            if(val == 0){
                v.setLabel("");
            }else{
                v.setLabel(emp.getNom()+" : "+val);
            }

            values.add(v);
            i++;
        }

        PieChartData pcd = new PieChartData(values);
        pcd.setHasLabels(true);
        pcd.setCenterText1FontSize(16);
        pcd.setHasCenterCircle(true).setCenterText1("Nombre d'employés");
        nb_employes.setPieChartData(pcd);
    }



    public void show_rentabilite(){
        // GRAPHIQUE DE RENTABILITE


        ArrayList<Employe> employes = new ArrayList<Employe>(((AcceuilActivity) getActivity()).getGameState().employes.values());
        List<SliceValue> values = new ArrayList<SliceValue>();

        PieChartView production = root.findViewById(R.id.production);
        values = new ArrayList<SliceValue>();

        int i = 0;

        if(tout_a_zero){
            for(Employe emp : employes){
                if(!(emp.getTotalRate()==0)){
                    tout_a_zero = false;
                }
            }
        }
        for(Employe emp : employes){
            float val = 1;
            if(!tout_a_zero){
                val = (float)emp.getTotalRate();
                val = (float)(Math.round(val * 100.0) / 100.0);
            }
            SliceValue v = new SliceValue(val,((AcceuilActivity) getActivity()).getGameState().colors[i] );
            if(val == 0){
                v.setLabel("");
            }else{
                v.setLabel(emp.getNom()+" : "+val);
            }

            values.add(v);
            i++;
        }

        PieChartData pcd = new PieChartData(values);
        pcd.setHasLabels(true);
        pcd.setCenterText1FontSize(16);
        pcd.setHasCenterCircle(true).setCenterText1("Productivité");
        production.setPieChartData(pcd);
    }



    public void show_Production_totale(){
        // GRAPHIQUE DE PRODUCTION

        ArrayList<Employe> employes = new ArrayList<Employe>(((AcceuilActivity) getActivity()).getGameState().employes.values());
        List<SliceValue> values = new ArrayList<SliceValue>();

        PieChartView production_totale = root.findViewById(R.id.prod_totale);
        values = new ArrayList<SliceValue>();

        int i = 0;

        if(tout_a_zero){
            for(Employe emp : employes){
                if(!(emp.getTotalRate()==0)){
                    tout_a_zero = false;
                }
            }
        }
        for(Employe emp : employes){
            float val = 1;
            if(!tout_a_zero){
                val = (float)emp.getTotalProduction();
                val = (float)(Math.round(val * 100.0) / 100.0);
            }
            SliceValue v = new SliceValue(val,((AcceuilActivity) getActivity()).getGameState().colors[i] );
            if(val == 0){
                v.setLabel("");
            }else{
                v.setLabel(emp.getNom()+" : "+val);
            }

            values.add(v);
            i++;
        }

        PieChartData pcd = new PieChartData(values);
        pcd.setHasLabels(true);
        pcd.setCenterText1FontSize(16);
        pcd.setHasCenterCircle(true).setCenterText1("Production\nTotale");
        production_totale.setPieChartData(pcd);

    }







}