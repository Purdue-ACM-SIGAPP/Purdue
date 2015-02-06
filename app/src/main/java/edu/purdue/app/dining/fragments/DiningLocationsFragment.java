package edu.purdue.app.dining.fragments;

import android.view.View;
import android.widget.AdapterView;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import edu.purdue.app.dining.data.DiningData;
import edu.purdue.app.dining.listeners.LocationsListener;
import edu.purdue.app.listeners.OnLoadedListener;
import edu.purdue.app.dining.models.Location;
import edu.purdue.app.fragments.MultiSelectCardListFragment;
import edu.purdue.app.widgets.CardViewListAdapter;

/**
 * Created by mike on 2/5/15.
 */
public class DiningLocationsFragment extends MultiSelectCardListFragment implements LocationsListener {

    private AdapterView.OnItemClickListener clickListener;
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

    /** The container activity sets itself as a listener for when clicks happen. Because the
     *  Fragment itself is a click listener, we act as a proxy here by intercepting the item
     *  click
     */
    public void setOnItemClickListener(AdapterView.OnItemClickListener listener) {
        this.clickListener = listener;
    }

    public Set<Integer> getSelectedItems() {
        return selectedItems;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        super.onItemClick(parent, view, position, id);

        // Super takes care of storing the thing that was clicked, but we need to alert
        // the activity that we were clicked as well
        this.clickListener.onItemClick(parent, view, position, id);

    }

}
