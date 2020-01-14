package com.example.applicationtest.ui.challenge;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.applicationtest.AcceuilActivity;
import com.example.applicationtest.R;
import com.example.applicationtest.challenge.Challenge;
import com.example.applicationtest.challenge.ChallengeAdapter;

import java.util.ArrayList;

public class Ten_seconds_fragment extends DialogFragment {
    private tenSecondsViewModel ten_secondsViewModel;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        ten_secondsViewModel =
                ViewModelProviders.of(this).get(tenSecondsViewModel.class);
        View root = inflater.inflate(R.layout.tenseconds_challenge_fragment, container, false);

        ArrayList<Challenge> chall = new ArrayList<Challenge>(((AcceuilActivity) getActivity()).getChallenges());

        System.out.println(chall + " -------------------------------------------------------");


        // Attach the adapter to a ListView

        ListView listView = root.findViewById(R.id.listChallenge);


        return root;
    }

}
