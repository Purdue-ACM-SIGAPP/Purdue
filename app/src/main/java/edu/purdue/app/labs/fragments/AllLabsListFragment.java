package edu.purdue.app.labs.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import java.util.List;

import edu.purdue.app.R;
import edu.purdue.app.labs.adapters.AllLabsListAdapter;
import edu.purdue.app.labs.adapters.LabsListAdapter;
import edu.purdue.app.labs.listeners.OnGetAllLabsListener;
import edu.purdue.app.labs.model.AvailableLab;
import edu.purdue.app.labs.tasks.GetAllTask;
import edu.purdue.app.labs.tasks.GetAvailableTask;

/**
 * Created by david on 9/8/14.
 */
public class AllLabsListFragment extends AsyncListFragment implements OnGetAllLabsListener {

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
        new GetAllTask(this).execute();
    }

    @Override
    public void onGetAllLabs(List<String> labs) {
        if(labs == null) showError();
        else {
            showList();
            if (getView() != null) {
                Log.d("LABS", "Adding adapter");
                ((ExpandableListView) getView().findViewById(android.R.id.list)).setAdapter(new AllLabsListAdapter(getActivity(), labs));
            }
        }
    }
}
