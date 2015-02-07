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
import java.util.SortedSet;
import java.util.TreeSet;

import edu.purdue.app.R;

/**
 *  A fragment which creates a list of cards and allows you to multiselect items in that list.
 *  To use this, subclass it then override onCreateView() to set your items
 *  you want to set an adapter for the GridView. The items themselves can be anything, just
 *  set an adapter in onCreateView()
 *  Created by mike on 2/5/15.
 */
public abstract class MultiSelectCardListFragment extends CardListFragment
        implements AdapterView.OnItemClickListener {

    protected SortedSet<Integer> selectedItems;

    private int transparent, highlighted;

    public MultiSelectCardListFragment() {
        this.selectedItems = new TreeSet<>();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        gridView.setOnItemClickListener(this);
        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        // Prepare the colors that we use to switch the view colors
        // This needs to be done when the fragment is attached to an activity because we
        // need the resources from the activity.
        transparent = getResources().getColor(android.R.color.transparent);
        highlighted = getResources().getColor(R.color.black40);

    }

    public SortedSet<Integer> getSelectedItems() {
        return new TreeSet<>(this.selectedItems);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        if (selectedItems.contains(position)) {
            // Remove the highlighting from the view
            view.setBackgroundColor(transparent);

            selectedItems.remove(position);

        } else {
            // Add highlighting to the view
            view.setBackgroundColor(highlighted);

            selectedItems.add(position);
        }

    }

}
