package edu.purdue.app.dining.data;

import android.view.Menu;

import org.joda.time.LocalDate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import edu.purdue.app.dining.listeners.DailyMenusListener;
import edu.purdue.app.dining.listeners.LocationsListener;
import edu.purdue.app.dining.listeners.MealTimesListener;
import edu.purdue.app.dining.models.DailyMenu;
import edu.purdue.app.dining.models.Location;
import edu.purdue.app.dining.models.Meal;
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
                    if (menus != null && ex == null) {
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
                    if (menus != null && ex == null) {
                        cachedMenus.put(date, menus);
                    }
                    listener.onGetDailyMenus(menus, ex);
                }
            }).execute();
        }

    }

    /** Returns locations. Accesses a cache if available */
    public void getLocations(final LocationsListener listener) {

        if (cachedLocations != null) {
            listener.onGetLocations(cachedLocations, null);

        } else {
            new GetDiningLocationsTask(new LocationsListener() {
                @Override
                public void onGetLocations(List<Location> locations, Exception ex) {
                    if (locations != null && ex == null) {
                        cachedLocations = locations;
                    }
                    listener.onGetLocations(locations, ex);
                }
            }).execute();
        }

    }

    /** Returns the times which meals are being served for the given day */
    public void getMealTimes(final LocalDate date, final MealTimesListener listener) {

        getAllDailyMenus(date, new DailyMenusListener() {
            @Override
            public void onGetDailyMenus(List<DailyMenu> menus, Exception ex) {

                if (menus == null || ex != null) {
                    listener.onGetTimes(null, ex);
                    return;
                }

                // Map the order of the meals to their names so we can paint them on the UI in order
                SortedMap<Integer, String> orderTimeStringMap = new TreeMap<>();
                for (DailyMenu menu : menus) {
                    for (Meal meal : menu.getMeals()) {
                        orderTimeStringMap.put(meal.getOrder(), meal.getName());
                    }
                }

                // This is some magic here if you think about it
                // For some reason the Set<> returned by a SortedMap<>'s keys is sorted. How?
                List<String> timeStrings = new ArrayList<>();
                for (Integer sortedOrder : orderTimeStringMap.keySet()) {
                    timeStrings.add(orderTimeStringMap.get(sortedOrder));
                }

                // Return it through the listener
                listener.onGetTimes(timeStrings, null);

            }
        });

    }

}
