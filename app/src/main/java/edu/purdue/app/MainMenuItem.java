package edu.purdue.app;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;

import java.util.ArrayList;
import java.util.List;

public class MainMenuItem implements CustomMenuItem {
    private final Drawable icon;
    private final String name;
    private final String url;
    private final float id;

    public MainMenuItem(Drawable icon, String name, String url, float id) {
        this.icon = icon;
        this.name = name;
        this.url = url;
        this.id = id;
    }

    public static List<MainMenuItem> getDefaultMainMenuItems(Resources r) {
        ArrayList<MainMenuItem> toReturn = new ArrayList<MainMenuItem>();
        toReturn.add(new MainMenuItem(r.getDrawable(R.drawable.ic_news), r.getString(R.string.news), "http://www.purdue.edu/newsroom/index.html", 1));
        toReturn.add(new MainMenuItem(r.getDrawable(R.drawable.ic_calendar), r.getString(R.string.calendar), "https://calendar.purdue.edu/", 2));
        toReturn.add(new MainMenuItem(r.getDrawable(R.drawable.ic_map), r.getString(R.string.map), "http://www.purdue.edu/campus_map/", 3));
        toReturn.add(new MainMenuItem(r.getDrawable(R.drawable.ic_mymail), r.getString(R.string.mymail), "https://mymail.purdue.edu/", 4));
        toReturn.add(new MainMenuItem(r.getDrawable(R.drawable.ic_menus), r.getString(R.string.menus), "http://www.housing.purdue.edu/menus/", 5));
        toReturn.add(new MainMenuItem(r.getDrawable(R.drawable.ic_labs), r.getString(R.string.labs), "https://lslab.ics.purdue.edu/icsWeb/AvailableStations", 6));
        toReturn.add(new MainMenuItem(r.getDrawable(R.drawable.ic_bus), r.getString(R.string.bus_routes), "http://citybus.doublemap.com/map/", 7));
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

    @Override
    public float getId() {
        return id;
    }

}
