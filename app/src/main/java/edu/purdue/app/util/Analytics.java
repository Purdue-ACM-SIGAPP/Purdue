package edu.purdue.app.util;

import android.app.Activity;
import android.content.Context;

import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Logger;
import com.google.android.gms.analytics.Tracker;

import edu.purdue.app.BuildConfig;
import edu.purdue.app.R;

/**
 * Created by david on 5/21/14 for Purdue
 */
public abstract class Analytics {

    public static final String APP_SCREEN = "/Application";
    public static final String MAIN_SCREEN = "/Application/Grid";
    public static final String WEB_SCREEN = "/Application/Web";
    public static final String SETTING_SCREEN = "/Application/Settings";

    private static Tracker appTracker;

    /** Initializes our analytics object
     *  I suppose this is synchronized so we do not mistakenly create two trackers across two different threads,
     *  though it seems kind of overkill... */
    public synchronized static void initialize(Context context) {

        // If apptracker has been initialized there's no need to proceed here
        if (appTracker != null) {
            return;
        }

        // Configure google analytics
        GoogleAnalytics analytics = GoogleAnalytics.getInstance(context);
        analytics.getLogger().setLogLevel(Logger.LogLevel.VERBOSE);
        analytics.setDryRun( BuildConfig.DEBUG );

        // Create our tracker object
        appTracker = analytics.newTracker(context.getString(R.string.analytics_id));

    }

    /** Sends an analytics event that a user has started a new session */
    public static void sendNewSession() {

        // Set the screen name to the root /Application screen
        appTracker.setScreenName(APP_SCREEN);

        // Start a new session with the hit.
        appTracker.send(new HitBuilders.AppViewBuilder()
                .setNewSession()
                .build());

    }

    /** Sends an analytics event that a user has visited a screen
     *  Im synchronizing this as well out of a concern that we are actually modifying a variable
     *  in the global tracker object every time we send an analytics hit. */
    public synchronized static void sendScreenView(String baseName, String... data) {

        StringBuilder dataStr = new StringBuilder(baseName);
        for (String datum : data) {
            dataStr.append("|");
            dataStr.append(datum);
        }

        // Set the screen name of the app tracker
        appTracker.setScreenName(dataStr.toString());

        // Send a screen view.
        appTracker.send(new HitBuilders.AppViewBuilder().build());

    }

    public static void sendEvent(Activity activity, String category, String action, String label) {

        // Build and send an Event.
        appTracker.send(new HitBuilders.EventBuilder()
                .setCategory(category)
                .setAction(action)
                .setLabel(label)
                .build());

    }

    public static void sendTiming(Activity activity, String category, String name, String label, long interval) {

        // Build and send timing.
        appTracker.send(new HitBuilders.TimingBuilder()
                .setCategory(category)
                .setValue(interval)
                .setVariable(name)
                .setLabel(label)
                .build());

    }
}
