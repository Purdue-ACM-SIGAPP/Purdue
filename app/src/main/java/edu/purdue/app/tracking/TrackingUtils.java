package edu.purdue.app.tracking;

import android.app.Activity;
import android.util.Log;

/**
 * Created by david on 5/21/14 for Purdue
 */
public final class TrackingUtils{

    public static void trackActivityOpen(Activity activity)
    {
        Log.d("TrackingUtils", activity.getLocalClassName());
    }
}
