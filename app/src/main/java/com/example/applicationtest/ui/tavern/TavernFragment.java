package com.example.applicationtest.ui.tavern;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.applicationtest.AcceuilActivity;
import com.example.applicationtest.R;
import com.example.applicationtest.employe.EmployeAdapter;

public class TavernFragment extends Fragment {

    private TavernViewModel tavernViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        tavernViewModel =
                ViewModelProviders.of(this).get(TavernViewModel.class);
        View root = inflater.inflate(R.layout.fragment_taverne, container, false);
        EmployeAdapter adapter = new EmployeAdapter(this.getContext(), ((AcceuilActivity) getActivity()).getGameState().employes);

        // Attach the adapter to a ListView

        ListView listView = root.findViewById(R.id.listTavern);

        listView.setAdapter(adapter);

        Log.i("lab", "onCreateView: ");

        return root;
    }
}