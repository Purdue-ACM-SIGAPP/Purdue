package edu.purdue.app.dining.data;

import org.joda.time.LocalDate;

import java.util.List;

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
        new GetAllDiningMenusTask(LocalDate.now(), new GetAllDiningMenusTask.AllMenusListener() {
            @Override
            public void onAllMenusResult(List<DailyMenu> menus, Exception ex) {
                todaysMenus = menus;
            }
        }).execute();
        new GetDiningLocationsTask(new GetDiningLocationsTask.GetDiningLocationsListener() {
            @Override
            public void onDiningLocationsResult(List<Location> locations, Exception ex) {
                cachedLocations = locations;
            }
        }).execute();
    }

    /** Returns the dining menus for the current day. Accesses cache if available (not yet implemented) */
    public void getAllDailyMenus(GetAllDiningMenusTask.AllMenusListener listener) {

        if (todaysMenus != null) {
            listener.onAllMenusResult(todaysMenus, null);

        } else {
            GetAllDiningMenusTask task = new GetAllDiningMenusTask(LocalDate.now(), listener);
            task.execute();
        }

    }

    /** Returns the dining menus for a given day. Accesses cache if available (not yet implemented) */
    public void getAllDailyMenus(LocalDate date, GetAllDiningMenusTask.AllMenusListener listener) {

        if (todaysMenus != null && date.equals(LocalDate.now())) {
            listener.onAllMenusResult(todaysMenus, null);

        } else {
            GetAllDiningMenusTask task = new GetAllDiningMenusTask(date, listener);
            task.execute();
        }

    }

    /** Returns locations. Accesses a cache if available */
    public void getLocations(GetDiningLocationsTask.GetDiningLocationsListener locationsListener) {

        if (cachedLocations != null) {
            locationsListener.onDiningLocationsResult(cachedLocations, null);

        } else {
            GetDiningLocationsTask task = new GetDiningLocationsTask(locationsListener);
            task.execute();
        }

    }

}
