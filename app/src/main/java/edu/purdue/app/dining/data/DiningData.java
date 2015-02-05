package edu.purdue.app.dining.data;

import android.view.Menu;

import org.joda.time.LocalDate;

import java.util.List;

import edu.purdue.app.dining.listeners.DailyMenusListener;
import edu.purdue.app.dining.listeners.LocationsListener;
import edu.purdue.app.dining.models.DailyMenu;
import edu.purdue.app.dining.models.Location;
import edu.purdue.app.dining.tasks.GetAllDiningMenusTask;
import edu.purdue.app.dining.tasks.GetDailyDiningMenuTask;
import edu.purdue.app.dining.tasks.GetDiningLocationsTask;

/**
 * Class which provides dining court data to the UI layer. This will be the class
 * that eventually implements caching.
 *
 * Created by mike on 2/4/15.
 */
public class DiningData {

    private static List<DailyMenu> todaysMenus;
    private static List<Location> cachedLocations;

    /** Precache daily menu data for today */
    public static void precache() {
        new GetAllDiningMenusTask(LocalDate.now(), new DailyMenusListener() {
            @Override
            public void onGetDailyMenus(List<DailyMenu> menus, Exception ex) {
                todaysMenus = menus;
            }
        }).execute();
        new GetDiningLocationsTask(new LocationsListener() {
            @Override
            public void onGetLocations(List<Location> locations, Exception ex) {
                cachedLocations = locations;
            }
        }).execute();
    }

    /** Returns the dining menus for the current day. Accesses cache if available (not yet implemented) */
    public void getAllDailyMenus(DailyMenusListener listener) {

        if (todaysMenus != null) {
            listener.onGetDailyMenus(todaysMenus, null);

        } else {
            GetAllDiningMenusTask task = new GetAllDiningMenusTask(LocalDate.now(), listener);
            task.execute();
        }

    }

    /** Returns the dining menus for a given day. Accesses cache if available (not yet implemented) */
    public void getAllDailyMenus(LocalDate date, DailyMenusListener listener) {

        if (todaysMenus != null && date.equals(LocalDate.now())) {
            listener.onGetDailyMenus(todaysMenus, null);

        } else {
            GetAllDiningMenusTask task = new GetAllDiningMenusTask(date, listener);
            task.execute();
        }

    }

    /** Returns locations. Accesses a cache if available */
    public void getLocations(LocationsListener listener) {

        if (cachedLocations != null) {
            listener.onGetLocations(cachedLocations, null);

        } else {
            GetDiningLocationsTask task = new GetDiningLocationsTask(listener);
            task.execute();
        }

    }

}
