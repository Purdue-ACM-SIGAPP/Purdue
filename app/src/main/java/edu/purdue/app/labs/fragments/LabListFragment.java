package edu.purdue.app.labs.fragments;



import android.app.Activity;
import android.app.ExpandableListActivity;
import android.app.ListFragment;
import android.os.Bundle;
import android.app.Fragment;
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
public class LabListFragment extends Fragment implements OnGetAvailableLabsListener {

    public LabListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.labs_lab_list, container, false);
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
            if(getView() != null)
                ((ExpandableListView)getView().findViewById(android.R.id.list)).setAdapter(new LabsListAdapter(getActivity(), labs));
        }
    }

    private void showError() {
        if(getView() == null) return;

        View list = getView().findViewById(android.R.id.list);
        View progress = getView().findViewById(android.R.id.empty);
        View error = getView().findViewById(R.id.errorView);

        list.setVisibility(View.GONE);
        progress.setVisibility(View.GONE);
        error.setVisibility(View.VISIBLE);
    }

    private void showEmpty() {
        if(getView() == null) return;

        View list = getView().findViewById(android.R.id.list);
        View progress = getView().findViewById(android.R.id.empty);
        View error = getView().findViewById(R.id.errorView);

        list.setVisibility(View.GONE);
        progress.setVisibility(View.VISIBLE);
        error.setVisibility(View.GONE);
    }

    private void showList() {
        if(getView() == null) return;

        View list = getView().findViewById(android.R.id.list);
        View progress = getView().findViewById(android.R.id.empty);
        View error = getView().findViewById(R.id.errorView);

        list.setVisibility(View.VISIBLE);
        progress.setVisibility(View.GONE);
        error.setVisibility(View.GONE);
    }
}
