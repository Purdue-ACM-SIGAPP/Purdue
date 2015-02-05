package edu.purdue.app.dining.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import edu.purdue.app.R;
import edu.purdue.app.dining.data.DiningData;
import edu.purdue.app.dining.models.DailyMenu;
import edu.purdue.app.dining.models.Location;
import edu.purdue.app.dining.models.Meal;
import edu.purdue.app.dining.tasks.GetAllDiningMenusTask;
import edu.purdue.app.dining.tasks.GetDiningLocationsTask;
import edu.purdue.app.dining.widgets.DiningMasterAdapter;

/**
 * Created by mike on 2/4/15.
 */
public class DiningMasterFragment extends Fragment
        implements GetDiningLocationsTask.GetDiningLocationsListener, GetAllDiningMenusTask.AllMenusListener {

    private GridView locationsList;
    private GridView timesList;

    @Nullable @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_dining_master, container, false);

        // Get the listview
        locationsList = (GridView) view.findViewById(R.id.dining_locations_list);
        timesList = (GridView) view.findViewById(R.id.dining_times_list);

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        // Get the list of locations
        DiningData data = new DiningData();
        data.getLocations(this);
        data.getAllDailyMenus(this);

    }


    @Override
    public void onDiningLocationsResult(List<Location> locations, Exception ex) {

        if (locations == null || ex != null) {
            return;
        }

        List<String> locationNames = new ArrayList<>();
        for (Location location : locations) {
            locationNames.add(location.getName());
        }

        DiningMasterAdapter locationAdapter = new DiningMasterAdapter(getActivity(), locationNames);
        this.locationsList.setAdapter(locationAdapter);

    }

    @Override
    public void onAllMenusResult(List<DailyMenu> menus, Exception ex) {

        if (menus == null || ex != null) {
            return;
        }

        // Get the maximum order of meals we have
        int max = -1;
        for (DailyMenu menu : menus) {
            for (Meal meal : menu.getMeals()) {
                max = meal.getOrder() > max ? meal.getOrder() : max;
            }
        }

        // Initialize a list of that length
        String[] timeNames = new String[max];

        // And add the name of each meal we downloaded
        for (DailyMenu menu :menus) {
            for (Meal meal : menu.getMeals()) {
                timeNames[meal.getOrder()-1] = meal.getName();
            }
        }

        DiningMasterAdapter timeAdapter = new DiningMasterAdapter(getActivity(), Arrays.asList(timeNames));
        this.timesList.setAdapter(timeAdapter);

    }

}
