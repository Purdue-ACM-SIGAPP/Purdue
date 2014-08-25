package edu.purdue.app.application;

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
import edu.purdue.app.listener.CustomOnSharedPreferenceChangeListener;

/**
 * Created by david on 5/19/14 for Purdue
 */
public class PurdueApplication extends Application {

    /**
     * Enum used to identify the tracker that needs to be used for tracking.
     *
     * A single tracker is usually enough for most purposes. In case you do need multiple trackers,
     * storing them all in Application object helps ensure that they are created only once per
     * application instance.
     */
    public enum TrackerName {
        APP_TRACKER
    }

    HashMap<TrackerName, Tracker> mTrackers = new HashMap<TrackerName, Tracker>();

    public synchronized Tracker getTracker(TrackerName trackerId) {
        if (!mTrackers.containsKey(trackerId)) {

            GoogleAnalytics analytics = GoogleAnalytics.getInstance(this);
            analytics.getLogger()
                    .setLogLevel(Logger.LogLevel.VERBOSE);

            analytics.setDryRun( BuildConfig.DEBUG ); //This should turn off/on tracking based on debug/release build.

            Tracker t = analytics.newTracker(getString(R.string.analytics_id));

            mTrackers.put(trackerId, t);
        }
        return mTrackers.get(trackerId);
    }

    @Override
    public void onCreate()
    {
        super.onCreate();
        SharedPreferences userPrefs = PreferenceManager.getDefaultSharedPreferences(this);

        userPrefs.registerOnSharedPreferenceChangeListener(new CustomOnSharedPreferenceChangeListener(this));





        Tracker t = getTracker(TrackerName.APP_TRACKER);

        // Set screen name.
        // Where path is a String representing the screen name.
        t.setScreenName("/Application");

        // Start a new session with the hit.
        t.send(new HitBuilders.AppViewBuilder()
                .setNewSession()
                .build());
    }
}
