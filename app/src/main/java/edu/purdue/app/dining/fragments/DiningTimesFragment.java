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
import java.util.SortedMap;
import java.util.TreeMap;

import edu.purdue.app.R;
import edu.purdue.app.dining.data.DiningData;
import edu.purdue.app.dining.listeners.DailyMenusListener;
import edu.purdue.app.dining.listeners.OnLoadedListener;
import edu.purdue.app.dining.models.DailyMenu;
import edu.purdue.app.dining.models.Meal;
import edu.purdue.app.fragments.MultiSelectCardListFragment;
import edu.purdue.app.widgets.CardViewListAdapter;

/**
 * Created by mike on 2/5/15.
 */
public class DiningTimesFragment extends MultiSelectCardListFragment implements DailyMenusListener {

    private OnLoadedListener loadedListener;

    public void beginLoad() {
        // Get the list of times
        DiningData data = new DiningData();
        data.getAllDailyMenus(this);
    }

    @Override
    public void onGetDailyMenus(List<DailyMenu> menus, Exception ex) {

        // Map the order of the meals to their names so we can paint them on the UI in order
        SortedMap<Integer, String> orderTimeStringMap = new TreeMap<>();
        for (DailyMenu menu : menus) {
            for (Meal meal : menu.getMeals()) {
                orderTimeStringMap.put(meal.getOrder(), meal.getName());
            }
        }

        // This is some magic here if you think about it
        // For some reason the Set<> returned by a SortedMap<>'s keys is sorted. How? I have no idea.
        List<String> timeStrings = new ArrayList<>();
        for (Integer sortedOrder : orderTimeStringMap.keySet()) {
            timeStrings.add(orderTimeStringMap.get(sortedOrder));
        }

        // Create and set the adapter
        CardViewListAdapter adapter = new CardViewListAdapter(getActivity(), timeStrings);
        gridView.setAdapter(adapter);

        // Alert that we're loaded
        loadedListener.onLoaded(this.getClass());

    }

    public void setOnLoadedListener(OnLoadedListener loadedListener) {
        this.loadedListener = loadedListener;
    }

}
