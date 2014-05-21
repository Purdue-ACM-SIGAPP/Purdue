package edu.purdue.app.prefs;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.android.gms.analytics.GoogleAnalytics;

/**
 * Created by david on 5/21/14 for Purdue
 */
public class CustomOnSharedPreferenceChangeListener implements SharedPreferences.OnSharedPreferenceChangeListener {

    Context context;

    public CustomOnSharedPreferenceChangeListener(Context context){
        this.context = context;
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences,
                                          String key) {

        if (key.equals(PrefConstants.PREF_TRACKING)) {
            GoogleAnalytics.getInstance(context.getApplicationContext()).setAppOptOut(sharedPreferences.getBoolean(key, false));
        } else {
            // Any additional changed preference handling.
        }
    }
}
