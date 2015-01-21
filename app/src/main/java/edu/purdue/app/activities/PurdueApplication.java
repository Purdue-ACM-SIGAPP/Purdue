package edu.purdue.app.activities;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Logger;
import com.google.android.gms.analytics.Tracker;

import java.util.HashMap;

import edu.purdue.app.BuildConfig;
import edu.purdue.app.R;
import edu.purdue.app.prefs.CustomOnSharedPreferenceChangeListener;
import edu.purdue.app.utility.Analytics;

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


    }
}
