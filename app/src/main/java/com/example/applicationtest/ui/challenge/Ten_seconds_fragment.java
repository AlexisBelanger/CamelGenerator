package com.example.applicationtest.ui.challenge;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.applicationtest.AcceuilActivity;
import com.example.applicationtest.R;
import com.example.applicationtest.challenge.Challenge;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class Ten_seconds_fragment extends DialogFragment {
    private tenSecondsViewModel ten_secondsViewModel;
    private int n = 0;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        ten_secondsViewModel =
                ViewModelProviders.of(this).get(tenSecondsViewModel.class);
        View root = inflater.inflate(R.layout.tenseconds_challenge_fragment, container, false);

        ArrayList<Challenge> chall = new ArrayList<Challenge>(((AcceuilActivity) getActivity()).getChallenges());

        System.out.println(chall + " -------------------------------------------------------");


        // Attach the adapter to a ListView

        ListView listView = root.findViewById(R.id.listChallenge);

        Button clic = (Button) root.findViewById(R.id.clic_challenge_button);
        TextView nb_clic = (TextView) root.findViewById(R.id.nb_clic);
        TextView remaining_time = (TextView) root.findViewById(R.id.timer);

        final DialogFragment df = this;


        clic.setOnClickListener(new View.OnClickListener() {
            boolean is_already_launched = false;

            @Override
            public void onClick(View view) {


                Timer timer = new Timer();

                if (!is_already_launched) {
                    is_already_launched = true;


                    timer.scheduleAtFixedRate(new TimerTask() {
                        int i = 100;

                        @Override
                        public void run() {

                            Runnable task = new Runnable() {

                                @Override
                                public void run() {

                                    remaining_time.setText(i / 10 + "." + i % 10);
                                    i--;
                                    if (i == -1) {
                                        timer.cancel();
                                        clic.setText("Envoyer");
                                        clic.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View view) {
                                                ((AcceuilActivity) getActivity()).addUser();

                                                (df).dismiss();
                                                if (((AcceuilActivity) getActivity()).getPhoneNumbers() != null || !((AcceuilActivity) getActivity()).getPhoneNumbers().isEmpty()) {
                                                    ((AcceuilActivity) getActivity()).pop_up_envoi(getClicks());
                                                }
                                                resetClicks();
                                            }
                                        });
                                    }
                                }
                            };
                            AsyncTask.execute(task);

                        }
                    }, 0, 100);
                }
                ((Ten_seconds_fragment) df).upClicks();
                nb_clic.setText(((Ten_seconds_fragment) df).getClicks() + "");


            }


        });


        return root;
    }


    public void upClicks() {
        Log.i("sms", "" + n);
        n++;
    }

    public int getClicks() {
        return n;
    }

    public void resetClicks() {
        n = 0;
    }

}
