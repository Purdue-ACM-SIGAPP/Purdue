package edu.purdue.app.dining.fragments;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import edu.purdue.app.R;
import edu.purdue.app.dining.data.DiningData;
import edu.purdue.app.dining.listeners.LocationsListener;
import edu.purdue.app.dining.listeners.OnLoadedListener;
import edu.purdue.app.dining.models.Location;
import edu.purdue.app.dining.tasks.GetDiningLocationsTask;
import edu.purdue.app.widgets.CardViewListAdapter;

/**
 * Created by mike on 2/5/15.
 */
public class DiningLocationsFragment extends Fragment implements LocationsListener, AdapterView.OnItemClickListener {

    private GridView locationsGrid;
    private Set<Integer> selectedLocations;
    private OnLoadedListener loadedListener;

    public DiningLocationsFragment() {
        this.selectedLocations = new HashSet<>();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the view
        View fragmentView = inflater.inflate(R.layout.frag_dining_master, container, false);

        // Get elements from the view
        locationsGrid = (GridView) fragmentView.findViewById(R.id.dining_master_gridview);
        locationsGrid.setOnItemClickListener(this);

        return fragmentView;
    }

    public void beginLoad() {
        // Get the list of locations
        DiningData data = new DiningData();
        data.getLocations(this);
    }

    @Override
    public void onGetLocations(List<Location> locations, Exception ex) {

        // Compose a list of string locations (for now)
        List<String> locationStrings = new ArrayList<>();
        for (Location location : locations) {
            locationStrings.add(location.getName());
        }

        // Create the adapter and set it to the gridview
        CardViewListAdapter adapter = new CardViewListAdapter(getActivity(), locationStrings);
        locationsGrid.setAdapter(adapter);

        // Alert that we're loaded
        loadedListener.onLoaded(this.getClass());

    }

    public void setOnLoadedListener(OnLoadedListener loadedListener) {
        this.loadedListener = loadedListener;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        if (selectedLocations.contains(position)) {
            selectedLocations.remove(position);

            // Remove the highlighting from the view
            view.setBackgroundColor(getResources().getColor(android.R.color.white));

        } else {
            selectedLocations.add(position);

            // Add highlighting to the view
            view.setBackgroundColor(getResources().getColor(R.color.black40));
        }

    }

}
