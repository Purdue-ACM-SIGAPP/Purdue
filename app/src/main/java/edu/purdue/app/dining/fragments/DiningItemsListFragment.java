package edu.purdue.app.dining.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import edu.purdue.app.R;
import edu.purdue.app.dining.models.Meal;
import edu.purdue.app.dining.widgets.DiningItemListAdapter;

/**
 * Created by mike on 2/7/15.
 */
public class DiningItemsListFragment extends Fragment {

    private Meal meal;
    private ListView listView;

    public static DiningItemsListFragment create(Meal meal) {
        DiningItemsListFragment fragment = new DiningItemsListFragment();
        fragment.meal = meal;
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dining_item_list, container, false);
        listView = (ListView) view.findViewById(R.id.dining_item_listview);

        // Set the adapter
        DiningItemListAdapter adapter = new DiningItemListAdapter(getActivity(), meal);
        listView.setAdapter(adapter);

        return view;
    }

}
