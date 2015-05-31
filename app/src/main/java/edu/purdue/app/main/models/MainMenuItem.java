package edu.purdue.app.main.models;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;

import java.util.ArrayList;
import java.util.List;

import edu.purdue.app.R;
import edu.purdue.app.dining.activities.DiningActivity;
import edu.purdue.app.maps.MapsActivity;
import edu.purdue.app.news.activities.NewsCategoryActivity;

/**
 *  The superclass for a set of main menu items which make up the main menu
 *  screen of the application. Each item has a name (label), drawable, and
 *  intent to fire an activity when clicked.
 *
 *  Created by david on 5/14/14 for Purdue
 */
public abstract class MainMenuItem {

    private String name;
    private Drawable drawable;
    private Intent intent;

    /** Constructor generic to every main menu item */
    public MainMenuItem(String name, Drawable drawable, Intent intent) {
        this.name = name;
        this.drawable = drawable;
        this.intent = intent;
    }

    /** Returns the name of the item */
    public String getName() {
        return this.name;
    }

    /** Returns a drawable which is the icon that sits on top of the item's name
     *  and will be clickable */
    public Drawable getDrawable() {
        return this.drawable;
    }

    /** Returns the new activity intent which will be fired when the item is clicked */
    public Intent getIntent() {
        return this.intent;
    }

    /** Returns a list of every menu item that we want to exist on the main screen */
    public static List<MainMenuItem> getDefaultMainMenuItems(final Context c) {

        final Resources r = c.getResources();

        ArrayList<MainMenuItem> toReturn = new ArrayList<MainMenuItem>() {{

            add(new ActivityMenuItem(c,
                    r.getString(R.string.main_menu_item_map), r.getDrawable(R.drawable.ic_map), MapsActivity.class));
            add(new ActivityMenuItem(c,
                    r.getString(R.string.main_menu_item_dining), r.getDrawable(R.drawable.ic_menus), DiningActivity.class));
            add(new WebViewMenuItem(c,
                    r.getString(R.string.main_menu_item_mail), r.getDrawable(R.drawable.ic_mymail), "https://mymail.purdue.edu/"));
            add(new WebViewMenuItem(c,
                    r.getString(R.string.main_menu_item_buses), r.getDrawable(R.drawable.ic_bus), "http://citybus.doublemap.com/map/"));
            add(new ActivityMenuItem(c,
                    r.getString(R.string.main_menu_item_news), r.getDrawable(R.drawable.ic_news), NewsCategoryActivity.class));
            add(new WebViewMenuItem(c,
                    r.getString(R.string.main_menu_item_calendar), r.getDrawable(R.drawable.ic_calendar), "https://calendar.purdue.edu/"));
            add(new WebViewMenuItem(c,
                    r.getString(R.string.main_menu_item_videos), r.getDrawable(R.drawable.ic_videos), "http://www.youtube.com/user/PurdueUniversity"));
            add(new WebViewMenuItem(c,
                    r.getString(R.string.main_menu_item_photos), r.getDrawable(R.drawable.ic_pictures), "http://purdue.photoshelter.com/gallery-list"));
            add(new WebViewMenuItem(c,
                    r.getString(R.string.main_menu_item_labs), r.getDrawable(R.drawable.ic_labs), "https://lslab.ics.purdue.edu/icsWeb/AvailableStations"));
            add(new WebViewMenuItem(c,
                    r.getString(R.string.main_menu_item_directory), r.getDrawable(R.drawable.ic_directory), "http://itap.purdue.edu/directory"));
            add(new WebViewMenuItem(c,
                    r.getString(R.string.main_menu_item_safety), r.getDrawable(R.drawable.ic_safety), "https://www.purdue.edu/emergency_preparedness/flipchart/index.html"));
            add(new WebViewMenuItem(c,
                    r.getString(R.string.main_menu_item_store), r.getDrawable(R.drawable.ic_store), "http://www.purdueofficialstore.com/"));

        }};

        return toReturn;

    }

}
