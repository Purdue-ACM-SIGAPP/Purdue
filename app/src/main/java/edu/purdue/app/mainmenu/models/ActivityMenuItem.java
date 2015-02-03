package edu.purdue.app.mainmenu.models;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;

/**
 * Created by david on 9/2/14.
 */
public class ActivityMenuItem extends MainMenuItem {

    public ActivityMenuItem(Context context, String name, Drawable icon, Class<? extends Activity> activity) {
        super(name, icon, createIntent(context, activity));
    }

    private static Intent createIntent(Context context, Class<? extends Activity> activity) {
        Intent intent = new Intent(context, activity);
        return intent;
    }

}
