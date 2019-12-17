package com.example.applicationtest.ui.lab;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.applicationtest.R;

public class LabFragment extends Fragment {


    private LabViewModel labViewModel;

    private View root;




    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        labViewModel =
                ViewModelProviders.of(this).get(LabViewModel.class);


        this.root = inflater.inflate(R.layout.fragment_lab, container, false);




        return root;


    }







}