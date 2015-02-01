package edu.purdue.app.activities;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import org.joda.time.LocalDate;

import java.util.List;

import edu.purdue.app.dining.models.DailyMenu;
import edu.purdue.app.dining.models.Location;
import edu.purdue.app.dining.tasks.GetAllDiningMenusTask;
import edu.purdue.app.dining.tasks.GetDailyDiningMenuTask;
import edu.purdue.app.dining.tasks.GetDiningLocationsTask;
import edu.purdue.app.dining.util.DiningLocationName;
import edu.purdue.app.prefs.CustomOnSharedPreferenceChangeListener;
import edu.purdue.app.util.Analytics;
import edu.purdue.app.util.Logger;
import edu.purdue.app.util.Services;

/**
 *  Created by david on 5/19/14 for Purdue
 */
public class PurdueApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        SharedPreferences userPrefs = PreferenceManager.getDefaultSharedPreferences(this);
        userPrefs.registerOnSharedPreferenceChangeListener(new CustomOnSharedPreferenceChangeListener(this));

        // Initialize analytics
        Analytics.initialize(this);

        // Register this as a new session
        Analytics.sendNewSession();

        // Start background services
        Services.startServices(this);

    }

}
