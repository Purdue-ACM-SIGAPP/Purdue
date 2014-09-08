package edu.purdue.app.labs.fragments;

import android.app.Activity;
import android.app.ListFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import edu.purdue.app.R;
import edu.purdue.app.labs.listeners.OnGetLabBuildingsListener;
import edu.purdue.app.labs.tasks.GetLabBuildingsTask;

/**
 * Created by david on 9/4/14.
 */
public class LabBuildingListFragment extends AsyncListFragment implements OnGetLabBuildingsListener {

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        new GetLabBuildingsTask(this).execute();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.labs_lab_list, container, false);
    }

    @Override
    public void onGetLabBuildings(Set<String> locations) {
        if(locations == null){
            showError();
        }
        else {
            if (getView() != null) {
                showList();
                getListView().setAdapter(new ArrayAdapter<String>(getActivity(), R.layout.labs_lab_listitem, R.id.lab_txt_location, asAlphabeticalList(locations)));
            }
        }
    }

    private List<String> asAlphabeticalList(Set<String> locations) {
        ArrayList<String> arr = new ArrayList<String>();
        arr.addAll(locations);
        Collections.sort(arr);
        return arr;
    }
}

