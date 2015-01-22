package edu.purdue.app.dining.tasks;

import android.os.AsyncTask;

import org.joda.time.LocalDate;

import java.util.ArrayList;
import java.util.List;

import edu.purdue.app.dining.models.DailyMenu;
import edu.purdue.app.dining.util.LocationName;

/**
 *  Task which calls GetDailyDiningMenuTask for each recognized dining court on campus
 *  and returns each menu.
 *  Created by mike on 1/22/15.
 */
public class GetAllMenusTask extends AsyncTask<Void, Void, List<DailyMenu>> {

    public interface AllMenusListener {
        public void onResult(List<DailyMenu> menus, Exception ex);
    }

    private LocalDate date;
    private AllMenusListener listener;
    private Exception exception;

    public GetAllMenusTask(LocalDate date) {
        this.date = date;
    }

    @Override
    protected List<DailyMenu> doInBackground(Void... params) {

        List<DailyMenu> menus = new ArrayList<DailyMenu>();

        for (LocationName locationName : LocationName.values()) {

            GetDailyDiningMenuTask task = new GetDailyDiningMenuTask(date, locationName);

            // This is execute synchronously because we are already on a background thread
            DailyMenu menu = task.doInBackground();

            // Check if an error occurred
            if (task.exception != null) {
                this.exception = task.exception;
                return null;
            }
            if (menu == null) {
                exception = new Exception("The menu returned by the API was null");
                return null;
            }

            // Add it to the master list
            menus.add(menu);

        }

        return menus;
    }

    @Override
    protected void onPostExecute(List<DailyMenu> dailyMenus) {
        if (listener != null) {
            listener.onResult(dailyMenus, exception);
        }
    }

}
