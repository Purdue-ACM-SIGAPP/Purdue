package edu.purdue.app.dining.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import org.joda.time.Duration;
import org.joda.time.LocalDate;

import java.util.List;

import edu.purdue.app.main.data.Cache;
import edu.purdue.app.dining.listeners.DailyMenusListener;
import edu.purdue.app.dining.listeners.LocationsListener;
import edu.purdue.app.dining.models.DailyMenu;
import edu.purdue.app.dining.models.Location;
import edu.purdue.app.dining.tasks.GetAllDiningMenusTask;
import edu.purdue.app.dining.tasks.GetDiningLocationsTask;

/**
 * Created by mike on 1/24/15.
 */
public class DiningUpdaterService extends Service implements
        LocationsListener,
        DailyMenusListener {

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        // Update locations, just because
        GetDiningLocationsTask locTask = new GetDiningLocationsTask();
        locTask.execute();

        // Update menus for today
        GetAllDiningMenusTask menuTask = new GetAllDiningMenusTask(LocalDate.now(), this);
        menuTask.execute();

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onGetLocations(List<Location> locations, Exception ex) {

        // Store these in shared preferences
        // TODO: Handle exception
        Cache.Framework cache = Cache.getDefaultCacheSystem(this);
        for (Location location : locations) {
            cache.cache( "dining_location", location, new Duration(Duration.parse("48h")) );
        }

    }


    @Override
    public void onGetDailyMenus(List<DailyMenu> menus, Exception ex) {

        // Store in sprefs
        // TODO: Handle exceptions
        Cache.Framework cache = Cache.getDefaultCacheSystem(this);
        for (DailyMenu menu : menus) {
            cache.cache( "dining_menu_" + LocalDate.now(), menu, new Duration(Duration.parse("48h")));
        }

    }

}
