package edu.purdue.app.labs.fragments;



import android.app.Activity;
import android.app.ExpandableListActivity;
import android.app.ListFragment;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.TextView;

import java.util.List;

import edu.purdue.app.R;
import edu.purdue.app.labs.adapters.LabsListAdapter;
import edu.purdue.app.labs.listeners.OnGetAvailableLabsListener;
import edu.purdue.app.labs.model.AvailableLab;
import edu.purdue.app.labs.tasks.GetAvailableTask;

/**
 * A simple {@link Fragment} subclass.
 *
 */
public class AvailableLabListFragment extends AsyncListFragment implements OnGetAvailableLabsListener {

    public AvailableLabListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.labs_lab_explist, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        showEmpty();
        new GetAvailableTask(this).execute();
    }

    @Override
    public void onGetLabs(List<AvailableLab> labs) {
        if(labs == null) showError();
        else {
            showList();
            if (getView() != null) {
                Log.d("LABS", "Adding adapter");
                ((ExpandableListView) getView().findViewById(android.R.id.list)).setAdapter(new LabsListAdapter(getActivity(), labs));
            }
        }
    }
}
