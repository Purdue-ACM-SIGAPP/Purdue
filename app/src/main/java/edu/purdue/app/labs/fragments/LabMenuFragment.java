package edu.purdue.app.labs.fragments;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import edu.purdue.app.R;

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
        ((Button)view.findViewById(R.id.labs_alllabs)).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.labs_availablelabs:
                getFragmentManager().beginTransaction().addToBackStack(AvailableLabListFragment.class.getName()).replace(R.id.content, new AvailableLabListFragment()).commit();
                break;
            case R.id.labs_alllabs:
                getFragmentManager().beginTransaction().addToBackStack(LabBuildingListFragment.class.getName()).replace(R.id.content, new LabBuildingListFragment()).commit();
        }
    }
}
