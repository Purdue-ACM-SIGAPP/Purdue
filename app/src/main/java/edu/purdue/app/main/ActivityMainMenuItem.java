package edu.purdue.app.main;

import android.app.Activity;
import android.graphics.drawable.Drawable;

/**
 * Created by david on 9/2/14.
 */
public class ActivityMainMenuItem extends MainMenuItem {

    public Class<? extends Activity> getActivity() {
        return activity;
    }

    private Class<? extends Activity> activity;

    public ActivityMainMenuItem(Drawable icon, String name, Class<? extends Activity> activity) {
        super(icon, name, null);
        this.activity = activity;
    }
}
