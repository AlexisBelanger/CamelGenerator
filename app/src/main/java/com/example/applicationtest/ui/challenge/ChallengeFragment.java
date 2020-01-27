package com.example.applicationtest.ui.challenge;

import android.content.ContentResolver;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.applicationtest.AcceuilActivity;
import com.example.applicationtest.R;
import com.example.applicationtest.challenge.Challenge;
import com.example.applicationtest.challenge.ChallengeAdapter;
import com.example.applicationtest.employe.Employe;
import com.example.applicationtest.employe.EmployeAdapter;
import com.example.applicationtest.employe.EmployeComparator;
import com.example.applicationtest.ui.tavern.TavernViewModel;

import java.util.ArrayList;
import java.util.Collections;

public class ChallengeFragment extends Fragment {

    private ArrayList<String> phoneNumbers = new ArrayList<>();

    private ChallengeViewModel challengeViewModel;


    private ChallengeAdapter adapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        ((AcceuilActivity) getActivity()).requestPermission();


        challengeViewModel =
                ViewModelProviders.of(this).get(ChallengeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_challenge, container, false);

        ArrayList<Challenge> chall = new ArrayList<Challenge>(((AcceuilActivity) getActivity()).getChallenges());

        System.out.println(chall+" -------------------------------------------------------");


        adapter = new ChallengeAdapter(this.getContext(), chall);


        // Attach the adapter to a ListView

        ListView listView = root.findViewById(R.id.listChallenge);

        listView.setAdapter(adapter);


        return root;
    }


    public ChallengeAdapter getAdapter() {
        return adapter;
    }


    public void addToPhoneNumbers(String num) {
        this.phoneNumbers.add(num);
    }

    public ArrayList<String> getPhoneNumbers() {
        return this.phoneNumbers;
    }
}