package edu.purdue.app.labs.fragments;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import edu.purdue.app.R;
import edu.purdue.app.labs.listeners.OnGetAvailableLabsListener;
import edu.purdue.app.labs.model.AvailableLab;
import edu.purdue.app.labs.tasks.GetAvailableTask;

public class LabMenuFragment extends Fragment implements View.OnClickListener {

    public LabMenuFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.labs_button_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ((Button)view.findViewById(R.id.labs_availablelabs)).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.labs_availablelabs:
                getFragmentManager().beginTransaction().addToBackStack(LabListFragment.class.getName()).replace(R.id.content, new LabListFragment()).commit();
                break;
        }
    }
}
