package edu.purdue.app;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;

import java.util.ArrayList;
import java.util.List;

public class MainMenuItem {
    public final Drawable icon;
    public final String name;
    public final String url;

    public MainMenuItem(Drawable icon, String name, String url) {
        this.icon = icon;
        this.name = name;
        this.url = url;
    }

    public static List<MainMenuItem> getDefaultMainMenuItems(Resources r) {
        ArrayList<MainMenuItem> toReturn = new ArrayList<MainMenuItem>();
        toReturn.add(new MainMenuItem(r.getDrawable(R.drawable.ic_news), "News", "http://www.purdue.edu/newsroom/index.html"));
        toReturn.add(new MainMenuItem(r.getDrawable(R.drawable.ic_calendar), "Calendar", "https://calendar.purdue.edu/"));
        toReturn.add(new MainMenuItem(r.getDrawable(R.drawable.ic_map), "Map", "http://www.purdue.edu/campus_map/"));
        toReturn.add(new MainMenuItem(r.getDrawable(R.drawable.ic_mymail), "MyMail", "https://mymail.purdue.edu/"));
        toReturn.add(new MainMenuItem(r.getDrawable(R.drawable.ic_menus), "Menus", "http://www.housing.purdue.edu/menus/"));
        toReturn.add(new MainMenuItem(r.getDrawable(R.drawable.ic_labs), "Labs", "https://lslab.ics.purdue.edu/icsWeb/AvailableStations"));
        toReturn.add(new MainMenuItem(r.getDrawable(R.drawable.ic_bus), "Bus Routes", "http://citybus.doublemap.com/map/"));
        //TODO: Add the rest of the icons
        return toReturn;
    }
}
