package edu.purdue.app.dining.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import java.util.ArrayList;
import java.util.List;

import edu.purdue.app.dining.data.DiningLocationName;
import edu.purdue.app.main.fragments.MultiSelectCardListFragment;
import edu.purdue.app.main.widgets.CardViewListAdapter;

/**
 * Created by mike on 2/5/15.
 */
public class DiningLocationsFragment extends MultiSelectCardListFragment {

    private AdapterView.OnItemClickListener clickListener;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);

        // We pull in the list of locations from our enum, not the API
        List<String> locations = new ArrayList<>();
        for (DiningLocationName name : DiningLocationName.values()) {
            locations.add(name.printableName());
        }

        CardViewListAdapter adapter = new CardViewListAdapter(getActivity(), locations);
        gridView.setAdapter(adapter);

        return view;
    }

    /** The container activity sets itself as a listener for when clicks happen. Because the
     *  Fragment itself is a click listener, we act as a proxy here by intercepting the item
     *  click
     */
    public void setOnItemClickListener(AdapterView.OnItemClickListener listener) {
        this.clickListener = listener;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        super.onItemClick(parent, view, position, id);

        // Super takes care of storing the thing that was clicked, but we need to alert
        // the activity that we were clicked as well
        this.clickListener.onItemClick(parent, view, position, id);
    }

}
