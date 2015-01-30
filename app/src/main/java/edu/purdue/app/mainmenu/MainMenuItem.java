package edu.purdue.app.mainmenu;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;

import java.util.ArrayList;
import java.util.List;

import edu.purdue.app.R;
import edu.purdue.app.news.NewsSelectorActivity;

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

            // Web view items
            add(new WebViewMenuItem(c, r.getString(R.string.map), r.getDrawable(R.drawable.ic_map), "http://www.purdue.edu/campus_map/"));
            add(new WebViewMenuItem(c, r.getString(R.string.menus), r.getDrawable(R.drawable.ic_menus), "http://www.housing.purdue.edu/menus/"));
            add(new WebViewMenuItem(c, r.getString(R.string.mymail), r.getDrawable(R.drawable.ic_mymail), "https://mymail.purdue.edu/"));
            add(new WebViewMenuItem(c, r.getString(R.string.bus_routes), r.getDrawable(R.drawable.ic_bus), "http://citybus.doublemap.com/map/"));
            add(new ActivityMenuItem(c, r.getString(R.string.news), r.getDrawable(R.drawable.ic_news), NewsSelectorActivity.class));
            add(new WebViewMenuItem(c, r.getString(R.string.calendar), r.getDrawable(R.drawable.ic_calendar), "https://calendar.purdue.edu/"));
            add(new WebViewMenuItem(c, "Videos", r.getDrawable(R.drawable.ic_videos), "http://www.youtube.com/user/PurdueUniversity"));
            add(new WebViewMenuItem(c, "Photos", r.getDrawable(R.drawable.ic_pictures), "http://purdue.photoshelter.com/gallery-list"));
            add(new WebViewMenuItem(c, r.getString(R.string.labs), r.getDrawable(R.drawable.ic_labs), "https://lslab.ics.purdue.edu/icsWeb/AvailableStations"));
            add(new WebViewMenuItem(c, "Directory", r.getDrawable(R.drawable.ic_directory), "http://itap.purdue.edu/directory"));
            add(new WebViewMenuItem(c, "Safety", r.getDrawable(R.drawable.ic_safety), "https://www.purdue.edu/emergency_preparedness/flipchart/index.html"));
            add(new WebViewMenuItem(c, "Store", r.getDrawable(R.drawable.ic_store), "http://www.purdueofficialstore.com/"));

            // In Progress
            // add(new MainMenuItem(c, r.getDrawable(R.drawable.ic_tours), "Tours", "http://play.google.com/store/apps/details?id=com.barz.tourguide.purdue"));
            // add(new MainMenuItem(c, r.getDrawable(R.drawable.ic_weather), "Weather Preparedness", "http://www.purdue.edu/newsroom/health_safety/weather.html"));
            // add(new MainMenuItem(c, r.getDrawable(R.drawable.ic_giving), "Giving", "https://donate.purdue.edu/Menu.aspx"));
            // add(new MainMenuItem(c, r.getDrawable(R.drawable.ic_purdueboard), "PurdueBoard", "http://www.purdue.edu/purdueboard/"));
            // add(new MainMenuItem(c, r.getDrawable(R.drawable.ic_launcher), "5 Students", "http://www.purdue.edu/fivestudents/"));



        }};

        return toReturn;

    }

}
