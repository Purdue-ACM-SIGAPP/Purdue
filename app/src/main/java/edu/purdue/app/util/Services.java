package edu.purdue.app.util;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import edu.purdue.app.dining.services.DiningUpdaterService;

/**
 *  Created by mike on 1/24/15.
 */
public abstract class Services {

    private static final String SP_NAME = "service-tracker";
    private static final String SPK_DINING_SCHEDULED = "service-dining-court-updater-scheduled";

    public static void startServices(Context context) {
        startDiningUpdater(context);
    }

    public static void startDiningUpdater(Context context) {

        // Run it once right now
        Intent i = new Intent(context, DiningUpdaterService.class);
        context.startService(i);

        // Schedule it to run every 6 hours if not already scheduled
        SharedPreferences sprefs = context.getSharedPreferences(SP_NAME, 0);
        if (sprefs.getBoolean(SPK_DINING_SCHEDULED, false)) {
            AlarmManager manager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
            manager.setRepeating(
                    AlarmManager.ELAPSED_REALTIME_WAKEUP,
                    AlarmManager.INTERVAL_HALF_DAY,
                    AlarmManager.INTERVAL_HALF_DAY,
                    PendingIntent.getService(context, 0, i, 0));
            sprefs.edit().putBoolean(SPK_DINING_SCHEDULED, true).commit();
        }

    }

}
