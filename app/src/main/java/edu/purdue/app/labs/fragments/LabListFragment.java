package edu.purdue.app.labs.fragments;

import android.app.Activity;
import android.app.ListFragment;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;

import java.util.Set;

import edu.purdue.app.labs.listeners.OnGetLabBuildingsListener;
import edu.purdue.app.labs.tasks.GetLabBuildingsTask;

/**
 * Created by david on 9/4/14.
 */
public class LabListFragment extends ListFragment implements OnGetLabBuildingsListener {

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        new GetLabBuildingsTask(this).execute();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onGetLabBuildings(Set<String> locations) {

    }
}

