package edu.purdue.app.dining.activities;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

import org.joda.time.LocalDate;

import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import edu.purdue.app.R;
import edu.purdue.app.dining.data.DiningData;
import edu.purdue.app.dining.data.DiningLocationName;
import edu.purdue.app.dining.data.DiningTime;
import edu.purdue.app.dining.fragments.DiningLocationsFragment;
import edu.purdue.app.dining.fragments.DiningTimesFragment;
import edu.purdue.app.dining.listeners.LocationsListener;
import edu.purdue.app.dining.listeners.MealTimesListener;
import edu.purdue.app.dining.models.Location;
import edu.purdue.app.listeners.OnLoadedListener;

/**
 * Created by mike on 2/4/15.
 */
public class DiningActivity extends Activity
        implements View.OnClickListener, AdapterView.OnItemClickListener {

    private DiningLocationsFragment locationsFragment;
    private DiningTimesFragment timesFragment;

    /** These are the query options the user makes when selecting what menus to see.
     *  They are set through various actions with the UI elsewhere in this activity. */
    private LocalDate selectedDate;
    private Set<DiningLocationName> selectedLocations;
    private Set<DiningTime> selectedTimes;

    /** The textview at the bottom which acts as a display for what is selected */
    private TextView selectedItemsDisplay;

    /** Keep copies of the data the lists are displaying in the context of the activity */
    private List<Location> locationList;
    private List<String> timesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set the content view
        setContentView(R.layout.activity_dining);

        // Instantiate our selected lists
        selectedLocations = new HashSet<>();
        selectedTimes = new HashSet<>();

        // Change the title of the activity to the current date
        // And store the current date as the currently selected date
        setTitle("Menus Today");
        selectedDate = LocalDate.now();

        // Cache copies of the two component fragment's data here
        getData();

        // Get instances of the two component fragments
        FragmentManager manager = getFragmentManager();
        locationsFragment =
                (DiningLocationsFragment) manager.findFragmentById(R.id.dining_locations_fragment);
        timesFragment =
                (DiningTimesFragment) manager.findFragmentById(R.id.dining_times_fragment);

        // And the textview at the bottom
        selectedItemsDisplay = (TextView) findViewById(R.id.dining_bottom_bar);
        selectedItemsDisplay.setOnClickListener(this);

        // Set the activity as an onclicklistener for the fragment
        locationsFragment.setOnItemClickListener(this);
        timesFragment.setOnItemClickListener(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.dining, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.dining_menu_yesterday:
                selectedDate = selectedDate.minusDays(1);
                break;
            case R.id.dining_menu_tomorrow:
                selectedDate = selectedDate.plusDays(1);
                break;
        }

        // Reset the title
        if (selectedDate.equals(LocalDate.now())) {
            setTitle("Menus Today");

        } else if (selectedDate.equals(LocalDate.now().plusDays(1))) {
            setTitle("Menus Tomorrow");

        } else if (selectedDate.equals(LocalDate.now().minusDays(1))) {
            setTitle("Menus Yesterday");

        } else {
            setTitle("Menus " + selectedDate.toString());
        }

        return true;
    }

    public void getData() {
        DiningData diningData = new DiningData();
        diningData.getLocations(new LocationsListener() {
            @Override
            public void onGetLocations(List<Location> locations, Exception ex) {
                locationList = locations;
            }
        });
        diningData.getMealTimes(LocalDate.now(), new MealTimesListener() {
            @Override
            public void onGetTimes(List<String> times, Exception ex) {
                timesList = times;
            }
        });
    }

    @Override
    public void onClick(View v) {



    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        // Rebuild the selected items sets
        // We could iteratively add and remove from them, but I'm actually not convinced that would
        // be faster because it would require two additional bits of knowledge we'd have to divine:
        // 1) Which list was actually clicked?
        // 2) When the item was clicked, was it selected or deselected?
        selectedLocations.clear();
        selectedTimes.clear();

        // This conversion is scary. And expensive as hell.
        // I might want to rewrite this one day so we don't have to exchange
        // strings and instead just exchange the enum type. Not today though.
        for (Integer i : timesFragment.getSelectedItems()) {
            selectedTimes.add(DiningTime.valueOf(timesList.get(i).toUpperCase().replace(" ", "")));
        }

        for (Integer i : locationsFragment.getSelectedItems()) {
            selectedLocations.add(DiningLocationName.valueOf(locationList.get(i).getName().toUpperCase()));
        }

        // Update the text string on the bottom textview with the newly clicked item
        StringBuilder sb = new StringBuilder();

        // Get the items the user has selected
        SortedSet<Integer> selectedTimes = timesFragment.getSelectedItems();
        SortedSet<Integer> selectedLocations = locationsFragment.getSelectedItems();

        // If they are both zero, display our "rundown"
        if (selectedTimes.size() == 0 && selectedLocations.size() == 0) {
            selectedItemsDisplay.setText("Let Us Pick For You");
            return;
        }

        int i = 0;
        if (selectedTimes.size() == 0) {
            sb.append("All Day");
        } else {
            for (Integer time : selectedTimes) {
                if (i++ == 0) {
                    sb.append(timesList.get(time));
                } else if (i == selectedTimes.size()) {
                    sb.append(" and " + timesList.get(time));
                } else {
                    sb.append(", " + timesList.get(time));
                }
            }
        }

        if (selectedLocations.size() == 0) {
            sb.append(" Everywhere");

        } else {
            sb.append(" at ");

            i = 0;
            for (Integer loc : selectedLocations) {
                if (i++ == 0) {
                    sb.append(locationList.get(loc).getName());
                } else if (i == selectedLocations.size()) {
                    sb.append(" and " + locationList.get(loc).getName());
                } else {
                    sb.append(", " + locationList.get(loc).getName());
                }
            }
        }

        // :)
        if (selectedLocations.size() == 5 && selectedTimes.size() == 4) {
            sb = new StringBuilder("Do you really want everything or did you just click them all because it looked like fun?");
        }

        // Set the size of the text
        // We make the text tinier if they have a lot selected so we can fit it all
        // This isnt an exact science lol
        if ((selectedLocations.size() + selectedTimes.size()) <= 2) {
            selectedItemsDisplay.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 25);
        } else if ((selectedLocations.size() + selectedTimes.size() <= 4)) {
            selectedItemsDisplay.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 20);
        } else if ((selectedLocations.size() + selectedTimes.size() <= 8)) {
            selectedItemsDisplay.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 15);
        }

        // Set it on the textview
        selectedItemsDisplay.setText(sb.toString());

    }

}
