package edu.purdue.app.dining.fragments;

import android.app.Fragment;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import edu.purdue.app.R;
import edu.purdue.app.dining.data.DiningData;
import edu.purdue.app.dining.data.DiningLocationName;
import edu.purdue.app.dining.models.DailyMenu;
import edu.purdue.app.dining.models.Location;
import edu.purdue.app.dining.models.Meal;
import edu.purdue.app.dining.tasks.GetAllDiningMenusTask;
import edu.purdue.app.dining.tasks.GetDiningLocationsTask;
import edu.purdue.app.dining.widgets.DiningMasterAdapter;

/**
 * Created by mike on 2/4/15.
 */
public class DiningMasterFragment extends Fragment implements
        GetDiningLocationsTask.GetDiningLocationsListener,
        GetAllDiningMenusTask.AllMenusListener,
        AdapterView.OnItemClickListener {

    // Grid views
    private GridView locationsList;
    private GridView timesList;

    // List of cached locations and cached times
    private List<String> locationNames;
    private List<String> timeNames;

    // The currently selected items
    private DiningLocationName selectedLocation;
    private int selectedLocationIndex;
    private String selectedTime;
    private int selectedTimeIndex;

    @Nullable @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_dining_master, container, false);

        // Get the listview
        locationsList = (GridView) view.findViewById(R.id.dining_locations_list);
        timesList = (GridView) view.findViewById(R.id.dining_times_list);

        // Set listener
        locationsList.setOnItemClickListener(this);
        timesList.setOnItemClickListener(this);

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

        this.locationNames = new ArrayList<>();
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

        this.timeNames = Arrays.asList(timeNames);
        DiningMasterAdapter timeAdapter = new DiningMasterAdapter(getActivity(), this.timeNames);
        this.timesList.setAdapter(timeAdapter);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        switch (parent.getId()) {
            case R.id.dining_locations_list:
                this.selectedLocation = DiningLocationName.fromName(locationNames.get(position));

                // Remove the color of the previous
                locationsList.getChildAt(selectedLocationIndex).setBackgroundColor(getResources().getColor(android.R.color.transparent));
                ((ViewGroup)locationsList.getChildAt(selectedLocationIndex)).getChildAt(0).setBackground(getResources().getColor(android.R.color.transparent));
                this.selectedLocationIndex = position;

                break;
            case R.id.dining_times_list:
                this.selectedTime = timeNames.get(position);

                // Remove the color of the previous
                timesList.getChildAt(this.selectedTimeIndex).setBackgroundColor(getResources().getColor(android.R.color.transparent));
                this.selectedTimeIndex = position;

                break;
        }

        // Set the background of the selected one
        if (Build.VERSION.SDK_INT > 16) {
            ((ViewGroup) view).getChildAt(0).setBackground(getResources().getDrawable(R.drawable.shadow_highlighted));
        } else {
            ((ViewGroup) view).getChildAt(0).setBackgroundDrawable(getResources().getDrawable(R.drawable.shadow_highlighted));
        }

    }

}
