package edu.purdue.app.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.HashSet;
import java.util.Set;

import edu.purdue.app.R;

/**
 *  A fragment which creates a list of cards and allows you to multiselect items in that list.
 *  To use this, subclass it then override onCreateView() to set your items
 *  you want to set an adapter for the GridView. The items themselves can be anything, just
 *  set an adapter in onCreateView()
 *  Created by mike on 2/5/15.
 */
public abstract class MultiSelectCardListFragment extends Fragment implements AdapterView.OnItemClickListener {

    protected GridView gridView;
    protected Set<Integer> selectedItems;

    private int white, highlighted;

    public MultiSelectCardListFragment() {
        this.selectedItems = new HashSet<>();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the view
        View fragmentView = inflater.inflate(R.layout.card_list, container, false);

        // Get elements from the view
        gridView = (GridView) fragmentView.findViewById(R.id.card_list_gridview);
        gridView.setOnItemClickListener(this);

        return fragmentView;

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        // Prepare the colors that we use to switch the view colors
        // This needs to be done when the fragment is attached to an activity because we
        // need the resources from the activity.
        white = getResources().getColor(android.R.color.white);
        highlighted = getResources().getColor(R.color.black40);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        if (selectedItems.contains(position)) {
            // Remove the highlighting from the view
            view.setBackgroundColor(white);

            selectedItems.remove(position);

        } else {
            // Add highlighting to the view
            view.setBackgroundColor(highlighted);

            selectedItems.add(position);
        }

    }

}
