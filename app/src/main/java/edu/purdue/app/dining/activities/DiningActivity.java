package edu.purdue.app.dining.activities;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

import edu.purdue.app.R;
import edu.purdue.app.dining.fragments.DiningLocationsFragment;
import edu.purdue.app.dining.fragments.DiningTimesFragment;
import edu.purdue.app.listeners.OnLoadedListener;

/**
 * Created by mike on 2/4/15.
 */
public class DiningActivity extends Activity
        implements OnLoadedListener, View.OnClickListener, AdapterView.OnItemClickListener {

    private ProgressDialog loadingDialog;
    private DiningLocationsFragment locationsFragment;
    private DiningTimesFragment timesFragment;
    private TextView selectedItemsDisplay;

    // Keep track when children are loaded
    private boolean locationsLoaded = false, timesLoaded = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set the content view LAST
        setContentView(R.layout.activity_dining);

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

        // Start a loading spinner
        startLoadingSpinner();

        // Tell the fragments to start loading
        // We move this method out of the onCreateView/onViewCreated call chain because the fragments
        // absolutely cannot start loading before we've told them to assign the parent activity as a
        // listener for when they are loaded, or else due to thread scheduling they might finish before
        // the activity is even set
        locationsFragment.beginLoad();
        timesFragment.beginLoad();

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

    }

}
