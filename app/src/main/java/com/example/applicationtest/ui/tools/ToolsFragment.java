package com.example.applicationtest.ui.tools;

import android.os.Bundle;
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
import com.example.applicationtest.employe.EmployeAdapter;

public class ToolsFragment extends Fragment {

    private ToolsViewModel toolsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        toolsViewModel =
                ViewModelProviders.of(this).get(ToolsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_test, container, false);
        EmployeAdapter adapter = new EmployeAdapter(this.getContext(), ((AcceuilActivity) getActivity()).getGameState().employes);

        // Attach the adapter to a ListView

        ListView listView = root.findViewById(R.id.listTest);

        listView.setAdapter(adapter);

        return root;
    }
}