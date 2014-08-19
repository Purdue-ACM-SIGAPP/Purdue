package edu.purdue.app.tracking;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

import edu.purdue.app.PurdueApplication;

/**
 * Created by david on 5/21/14 for Purdue
 */
public final class TrackingUtils{

    public static final String APP_SCREEN = "/Application";
    public static final String MAIN_SCREEN = "/Application/Grid";
    public static final String WEB_SCREEN = "/Application/Web";
    public static final String SETTING_SCREEN = "/Application/Settings";

    public static void sendScreenView(Activity activity, String baseName, String... data) {
        // Get tracker.
        Tracker t = ((PurdueApplication) activity.getApplication()).getTracker(
                PurdueApplication.TrackerName.APP_TRACKER);

        StringBuilder sb = new StringBuilder(baseName);
        for (String foo : data) {
            sb.append(" | ");
            sb.append(foo);
        }
        t.setScreenName(sb.toString());

        // Send a screen view.
        t.send(new HitBuilders.AppViewBuilder().build());
    }

    public static void sendEvent(Activity activity, String category, String action, String label) {
        // Get tracker.
        Tracker t = ((PurdueApplication) activity.getApplication()).getTracker(
                PurdueApplication.TrackerName.APP_TRACKER);
        // Build and send an Event.
        t.send(new HitBuilders.EventBuilder()
                .setCategory(category)
                .setAction(action)
                .setLabel(label)
                .build());
    }

    public static void sentTiming(Activity activity, String category, String name, String label, long interval) {
        // Get tracker.
        Tracker t = ((PurdueApplication) activity.getApplication()).getTracker(
                PurdueApplication.TrackerName.APP_TRACKER);

        // Build and send timing.
        t.send(new HitBuilders.TimingBuilder()
                .setCategory(category)
                .setValue(interval)
                .setVariable(name)
                .setLabel(label)
                .build());
    }
}
