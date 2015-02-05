package edu.purdue.app.dining.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.List;

import edu.purdue.app.R;
import edu.purdue.app.dining.data.DiningData;
import edu.purdue.app.dining.models.Location;
import edu.purdue.app.dining.tasks.GetDiningLocationsTask;
import edu.purdue.app.dining.widgets.DiningMasterAdapter;

/**
 * Created by mike on 2/5/15.
 */
public class DiningLocationsFragment extends Fragment implements GetDiningLocationsTask.GetDiningLocationsListener {

    private GridView locationsGrid;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the view
        View fragmentView = inflater.inflate(R.layout.frag_dining_master, container, false);

        // Get elements from the view
        locationsGrid = (GridView) fragmentView.findViewById(R.id.dining_master_gridview);

        return fragmentView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        // Get the list of locations
        DiningData data = new DiningData();
        data.getLocations(this);
    }

    @Override
    public void onDiningLocationsResult(List<Location> locations, Exception ex) {

        // Compose a list of string locations (for now)
        List<String> locationStrings = new ArrayList<>();
        for (Location location : locations) {
            locationStrings.add(location.getName());
        }

        // Create the adapter and set it to the gridview
        DiningMasterAdapter adapter = new DiningMasterAdapter(getActivity(), locationStrings);
        locationsGrid.setAdapter(adapter);

    }

}
