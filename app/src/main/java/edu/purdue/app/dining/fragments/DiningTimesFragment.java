package edu.purdue.app.dining.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import org.joda.time.LocalDate;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

import edu.purdue.app.dining.data.DiningData;
import edu.purdue.app.dining.data.DiningTime;
import edu.purdue.app.dining.listeners.DailyMenusListener;
import edu.purdue.app.dining.listeners.MealTimesListener;
import edu.purdue.app.listeners.OnLoadedListener;
import edu.purdue.app.dining.models.DailyMenu;
import edu.purdue.app.dining.models.Meal;
import edu.purdue.app.fragments.MultiSelectCardListFragment;
import edu.purdue.app.widgets.CardViewListAdapter;

/**
 * Created by mike on 2/5/15.
 */
public class DiningTimesFragment extends MultiSelectCardListFragment {

    private AdapterView.OnItemClickListener clickListener;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);

        // Get the list of times from the enum
        List<String> times = new ArrayList<>();
        for (DiningTime time : DiningTime.values()) {
            times.add(time.printable());
        }

        CardViewListAdapter adapter = new CardViewListAdapter(getActivity(), times);
        gridView.setAdapter(adapter);

        return view;
    }

    public void setOnItemClickListener(AdapterView.OnItemClickListener clickListener) {
        this.clickListener = clickListener;
    }

    /** Just like the locations fragment. We act as a proxy in this class by intercepting the item click,
     *  calling through to super, then re-calling the item click listener which the activity sets. */
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        super.onItemClick(parent, view, position, id);
        this.clickListener.onItemClick(parent, view, position, id);
    }

}
