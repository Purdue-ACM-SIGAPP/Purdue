package edu.purdue.app.dining.activities;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

import org.joda.time.LocalDate;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import edu.purdue.app.R;
import edu.purdue.app.dining.data.DiningData;
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
        implements OnLoadedListener, View.OnClickListener, AdapterView.OnItemClickListener {

    private ProgressDialog loadingDialog;
    private DiningLocationsFragment locationsFragment;
    private DiningTimesFragment timesFragment;

    /** The textview at the bottom which acts as a display for what is selected */
    private TextView selectedItemsDisplay;

    /** Keep track when children are loaded */
    private boolean locationsLoaded = false, timesLoaded = false;

    /** Keep copies of the data the lists are displaying in the context of the activity */
    private List<Location> locationList;
    private List<String> timesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set the content view
        setContentView(R.layout.activity_dining);

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

        // Set this activity as a listener for when the data has finished loading
        locationsFragment.setOnLoadedListener(this);
        timesFragment.setOnLoadedListener(this);

        // Set the activity as an onclicklistener for the fragment
        locationsFragment.setOnItemClickListener(this);
        timesFragment.setOnItemClickListener(this);

        // Start a loading spinner
        startLoadingSpinner();

        // Tell the fragments to start loading
        // We move this method out of the onCreateView/onViewCreated call chain because the fragments
        // absolutely cannot start loading before we've told them to assign the parent activity as a
        // listener for when they are loaded, or else due to thread scheduling they might finish before
        // the activity is set
        locationsFragment.beginLoad();
        timesFragment.beginLoad();

    }

    /** This might seem strange at first, and it kind of is.
     *  The fragments make a call to DiningData to get the data they need for the UI, and we make
     *  a separate call here to do the same thing. So why aren't we sharing the same data? We
     *  actually are. DiningData will maintain an in-memory cache of the data when it is downloaded
     *  the first time then just return that each time. So the copy of the data in this class
     *  and the copy in each fragment are actually exactly the same in memory.
     */
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

    public void startLoadingSpinner() {
        loadingDialog = new ProgressDialog(this);
        loadingDialog.setMessage("Please wait...");
        loadingDialog.setCancelable(false);
        loadingDialog.show();
    }

    public void stopLoadingSpinner() {
        loadingDialog.cancel();
    }

    @Override
    public void onLoaded(Class loaded) {

        if (loaded.equals(DiningLocationsFragment.class)) {
            locationsLoaded = true;
        }
        else if (loaded.equals(DiningTimesFragment.class)) {
            timesLoaded = true;
        }

        if (locationsLoaded && timesLoaded) {
            stopLoadingSpinner();
        }

    }

    @Override
    public void onClick(View v) {



    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        // Update the text string on the bottom textview with the newly clicked item
        StringBuilder sb = new StringBuilder();

        // Get the items the user has selected
        SortedSet<Integer> selectedTimes = timesFragment.getSelectedItems();
        SortedSet<Integer> selectedLocations = locationsFragment.getSelectedItems();

        // If they are both zero, just hide the textview
        if (selectedTimes.size() == 0 && selectedLocations.size() == 0) {
            selectedItemsDisplay.setText("");
            selectedItemsDisplay.setVisibility(View.INVISIBLE);
            selectedItemsDisplay.setBackgroundColor(getResources().getColor(android.R.color.transparent));
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

        // Make sure the textview is visible
        selectedItemsDisplay.setVisibility(View.VISIBLE);
        selectedItemsDisplay.setBackgroundColor(getResources().getColor(R.color.old_gold));

    }

}
