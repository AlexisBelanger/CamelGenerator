package com.example.applicationtest.ui.gallery;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.applicationtest.AcceuilActivity;
import com.example.applicationtest.Amelioration.Balkany;
import com.example.applicationtest.Amelioration.CodeMaster;
import com.example.applicationtest.Amelioration.Fortnite;
import com.example.applicationtest.Amelioration.Nord;
import com.example.applicationtest.GameState;
import com.example.applicationtest.LabActivity;
import com.example.applicationtest.MainActivity;
import com.example.applicationtest.R;
import com.example.applicationtest.ui.home.HomeViewModel;

import java.util.Timer;
import java.util.TimerTask;

public class GalleryFragment extends Fragment {


    private GalleryViewModel galleryViewModel;

    private View root;




    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {




        galleryViewModel =
                ViewModelProviders.of(this).get(GalleryViewModel.class);


        this.root = inflater.inflate(R.layout.fragment_lab, container, false);




        return root;


    }







}