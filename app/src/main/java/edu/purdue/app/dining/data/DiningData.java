package edu.purdue.app.dining.data;

import android.view.Menu;

import org.joda.time.LocalDate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

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

    private static Map<LocalDate, List<DailyMenu>> cachedMenus;
    private static List<Location> cachedLocations;

    static {
        cachedMenus = new HashMap<>();
    }

    /** Precache daily menu data for today */
    public static void precache() {
        new GetAllDiningMenusTask(LocalDate.now(), new DailyMenusListener() {
            public void onGetDailyMenus(List<DailyMenu> menus, Exception ex) {
                if (menus != null) {
                    cachedMenus.put(LocalDate.now(), menus);
                }
            }
        }).execute();
        new GetDiningLocationsTask(new LocationsListener() {
            @Override
            public void onGetLocations(List<Location> locations, Exception ex) {
                if (locations != null) {
                    cachedLocations = locations;
                }
            }
        }).execute();
    }

    /** Returns the dining menus for the current day. Accesses cache if available (not yet implemented) */
    public void getAllDailyMenus(final DailyMenusListener listener) {

        if (cachedMenus.get(LocalDate.now()) != null) {
            listener.onGetDailyMenus(cachedMenus.get(LocalDate.now()), null);

        } else {
            new GetAllDiningMenusTask(LocalDate.now(), new DailyMenusListener() {
                public void onGetDailyMenus(List<DailyMenu> menus, Exception ex) {
                    if (menus == null || ex != null) {
                        cachedMenus.put(LocalDate.now(), menus);
                    }
                    listener.onGetDailyMenus(menus, ex);
                }
            }).execute();
        }

    }

    /** Returns the dining menus for a given day. Accesses cache if available (not yet implemented) */
    public void getAllDailyMenus(final LocalDate date, final DailyMenusListener listener) {

        if (cachedMenus.get(date) != null) {
            listener.onGetDailyMenus(cachedMenus.get(date), null);

        } else {
            new GetAllDiningMenusTask(date, new DailyMenusListener() {
                public void onGetDailyMenus(List<DailyMenu> menus, Exception ex) {
                    if (menus == null || ex != null) {
                        cachedMenus.put(date, menus);
                    }
                    listener.onGetDailyMenus(menus, ex);
                }
            }).execute();
        }

    }

    /** Returns locations. Accesses a cache if available */
    public void getLocations(LocationsListener listener) {

        if (cachedLocations != null) {
            listener.onGetLocations(cachedLocations, null);

        } else {
            new GetDiningLocationsTask(listener).execute();
        }

    }

}
