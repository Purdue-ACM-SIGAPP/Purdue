package edu.purdue.app.dining.fragments;

import android.app.Fragment;
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
import edu.purdue.app.fragments.MultiSelectCardListFragment;
import edu.purdue.app.widgets.CardViewListAdapter;

/**
 * Created by mike on 2/5/15.
 */
public class DiningLocationsFragment extends MultiSelectCardListFragment implements LocationsListener {

    private OnLoadedListener loadedListener;

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
        gridView.setAdapter(adapter);

        // Alert that we're loaded
        loadedListener.onLoaded(this.getClass());

    }

    public void setOnLoadedListener(OnLoadedListener loadedListener) {
        this.loadedListener = loadedListener;
    }

}
