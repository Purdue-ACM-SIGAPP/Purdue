package edu.purdue.app.main;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;

import java.util.ArrayList;
import java.util.List;

import edu.purdue.app.R;

public class MainMenuItem implements CustomMenuItem {
    private final Drawable icon;
    private final String name;
    private final String url;

    public MainMenuItem(Drawable icon, String name, String url) {
        this.icon = icon;
        this.name = name;
        this.url = url;
    }

    public static List<MainMenuItem> getDefaultMainMenuItems(Resources r) {
        ArrayList<MainMenuItem> toReturn = new ArrayList<MainMenuItem>();

        /*
        To add an item to the main screen, add a MainMenuItem entry to the toReturn arraylist.

        To add an item that links to a webview:
                toReturn.add(new MainMenuItem(r.getDrawable(R.drawable.ic_map), r.getString(R.string.map), "http://www.purdue.edu/campus_map/"));
        To add an item that opens an activity:
                toReturn.add(new ActivityMainMenuItem(r.getDrawable(R.drawable.ic_labs), r.getString(R.string.labs), TestActivity.class));
         */

        toReturn.add(new MainMenuItem(r.getDrawable(R.drawable.ic_map), r.getString(R.string.map), "http://www.purdue.edu/campus_map/"));
        toReturn.add(new MainMenuItem(r.getDrawable(R.drawable.ic_menus), r.getString(R.string.menus), "http://www.housing.purdue.edu/menus/"));
        toReturn.add(new MainMenuItem(r.getDrawable(R.drawable.ic_mymail), r.getString(R.string.mymail), "https://mymail.purdue.edu/"));
        toReturn.add(new MainMenuItem(r.getDrawable(R.drawable.ic_bus), r.getString(R.string.bus_routes), "http://citybus.doublemap.com/map/"));

        toReturn.add(new MainMenuItem(r.getDrawable(R.drawable.ic_news), r.getString(R.string.news), "http://www.purdue.edu/newsroom/index.html"));
        toReturn.add(new MainMenuItem(r.getDrawable(R.drawable.ic_calendar), r.getString(R.string.calendar), "https://calendar.purdue.edu/"));
        toReturn.add(new MainMenuItem(r.getDrawable(R.drawable.ic_videos), "Videos", "http://www.youtube.com/user/PurdueUniversity"));
        toReturn.add(new MainMenuItem(r.getDrawable(R.drawable.ic_pictures), "Photos", "http://purdue.photoshelter.com/gallery-list"));
        toReturn.add(new MainMenuItem(r.getDrawable(R.drawable.ic_labs), r.getString(R.string.labs), "https://lslab.ics.purdue.edu/icsWeb/AvailableStations"));

        //Not necessary for this version, but I'm putting them here anyway
        //toReturn.add(new MainMenuItem(r.getDrawable(R.drawable.ic_tours), "Tours", "http://play.google.com/store/apps/details?id=com.barz.tourguide.purdue"));
        toReturn.add(new MainMenuItem(r.getDrawable(R.drawable.ic_directory), "Directory", "http://itap.purdue.edu/directory"));
        toReturn.add(new MainMenuItem(r.getDrawable(R.drawable.ic_safety), "Safety", "https://www.purdue.edu/emergency_preparedness/flipchart/index.html"));
//        toReturn.add(new MainMenuItem(r.getDrawable(R.drawable.ic_weather), "Weather Preparedness", "http://www.purdue.edu/newsroom/health_safety/weather.html"));
        //toReturn.add(new MainMenuItem(r.getDrawable(R.drawable.ic_giving), "Giving", "https://donate.purdue.edu/Menu.aspx"));
//        toReturn.add(new MainMenuItem(r.getDrawable(R.drawable.ic_purdueboard), "PurdueBoard", "http://www.purdue.edu/purdueboard/"));
        toReturn.add(new MainMenuItem(r.getDrawable(R.drawable.ic_store), "Store", "http://www.purdueofficialstore.com/"));
        //this one has no icon...
        //toReturn.add(new MainMenuItem(r.getDrawable(R.drawable.ic_launcher), "5 Students", "http://www.purdue.edu/fivestudents/"));

        //TODO: Add the rest of the icons
        return toReturn;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Drawable getDrawable() {
        return icon;
    }

    public String getUrl() {
        return url;
    }

}
