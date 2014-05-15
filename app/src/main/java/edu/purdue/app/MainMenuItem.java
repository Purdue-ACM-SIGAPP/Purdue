package edu.purdue.app;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;

import java.util.ArrayList;
import java.util.List;

public class MainMenuItem implements CustomMenuItem {
    public final Drawable icon;
    public final String name;
    public final String url;
    public final float id;

    public MainMenuItem(Drawable icon, String name, String url, float id) {
        this.icon = icon;
        this.name = name;
        this.url = url;
        this.id = id;
    }

    public static List<MainMenuItem> getDefaultMainMenuItems(Resources r) {
        ArrayList<MainMenuItem> toReturn = new ArrayList<MainMenuItem>();
        toReturn.add(new MainMenuItem(r.getDrawable(R.drawable.ic_news), "News", "http://www.purdue.edu/newsroom/index.html", 1));
        toReturn.add(new MainMenuItem(r.getDrawable(R.drawable.ic_calendar), "Calendar", "https://calendar.purdue.edu/", 2));
        toReturn.add(new MainMenuItem(r.getDrawable(R.drawable.ic_map), "Map", "http://www.purdue.edu/campus_map/", 3));
        toReturn.add(new MainMenuItem(r.getDrawable(R.drawable.ic_mymail), "MyMail", "https://mymail.purdue.edu/", 4));
        toReturn.add(new MainMenuItem(r.getDrawable(R.drawable.ic_menus), "Menus", "http://www.housing.purdue.edu/menus/", 5));
        toReturn.add(new MainMenuItem(r.getDrawable(R.drawable.ic_labs), "Labs", "https://lslab.ics.purdue.edu/icsWeb/AvailableStations", 6));
        toReturn.add(new MainMenuItem(r.getDrawable(R.drawable.ic_bus), "Bus Routes", "http://citybus.doublemap.com/map/", 7));
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

    @Override
    public float getId() {
        return id;
    }
}
