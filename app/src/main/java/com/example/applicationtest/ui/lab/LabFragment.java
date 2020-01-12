package com.example.applicationtest.ui.lab;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.applicationtest.AcceuilActivity;
import com.example.applicationtest.Amelioration.Amelioration;
import com.example.applicationtest.Amelioration.AmeliorationAdapter;
import com.example.applicationtest.Amelioration.AmeliorationComparator;
import com.example.applicationtest.R;
import com.example.applicationtest.employe.Employe;
import com.example.applicationtest.employe.EmployeAdapter;
import com.example.applicationtest.employe.EmployeComparator;
import com.example.applicationtest.ui.tavern.TavernViewModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class LabFragment extends Fragment {


    private LabViewModel labViewModel;

    private View root;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        labViewModel =
                ViewModelProviders.of(this).get(LabViewModel.class);
        View root = inflater.inflate(R.layout.fragment_lab, container, false);


        ArrayList<Amelioration> al = new ArrayList<Amelioration>(((AcceuilActivity) getActivity()).getGameState().ameliorations.values());

        Collections.sort(al, new AmeliorationComparator());

        AmeliorationAdapter adapter = new AmeliorationAdapter(this.getContext(), al);

        // Attach the adapter to a ListView

        ListView listView = root.findViewById(R.id.listLab);

        listView.setAdapter(adapter);

        Log.i("Tavern", "onCreateView: ");


        return root;


    }


}