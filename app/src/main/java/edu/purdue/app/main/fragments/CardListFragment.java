package edu.purdue.app.main.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import edu.purdue.app.R;

/**
 * Created by mike on 2/5/15.
 */
public class CardListFragment extends Fragment {

    protected GridView gridView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the view
        View fragmentView = inflater.inflate(R.layout.card_list, container, false);

        // Get elements from the view
        gridView = (GridView) fragmentView.findViewById(R.id.card_list_gridview);

        return fragmentView;

    }

}
