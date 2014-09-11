package edu.purdue.app.labs.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;

import java.util.List;

import edu.purdue.app.R;
import edu.purdue.app.labs.adapters.AllLabsListAdapter;
import edu.purdue.app.labs.adapters.LabsListAdapter;
import edu.purdue.app.labs.listeners.OnGetAllLabsListener;
import edu.purdue.app.labs.tasks.GetAllTask;
import edu.purdue.app.labs.tasks.GetAvailableTask;


public class AllLabsListFragment extends AsyncListFragment implements OnGetAllLabsListener, ExpandableListView.OnChildClickListener {

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
    public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
        Log.d("LIST", "On Child Click");
        String lab = parent.getExpandableListAdapter().getChild(groupPosition, childPosition).toString();
        getFragmentManager().beginTransaction().replace(R.id.content, LabDetailsFragment.newInstance(lab)).commit();
        return true;
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
                ExpandableListView lv = ((ExpandableListView) getView().findViewById(android.R.id.list));
                ExpandableListAdapter ada = new AllLabsListAdapter(getActivity(), labs);
                lv.setAdapter(ada);
                lv.setOnChildClickListener(this);
                for(int i = 0; i < ada.getGroupCount(); i++) {
                    lv.expandGroup(i);
                }

            }
        }
    }
}
