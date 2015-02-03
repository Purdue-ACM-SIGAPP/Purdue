package edu.purdue.app.dining.tasks;

import android.os.AsyncTask;

import org.joda.time.LocalDate;

import java.util.ArrayList;
import java.util.List;

import edu.purdue.app.dining.models.DailyMenu;
import edu.purdue.app.dining.data.DiningLocationName;

/**
 *  Task which calls GetDailyDiningMenuTask for each recognized dining court on campus
 *  and returns each menu.
 *  Created by mike on 1/22/15.
 */
public class GetAllDiningMenusTask extends AsyncTask<Void, Void, List<DailyMenu>> {

    public interface AllMenusListener {
        public void onAllMenusResult(List<DailyMenu> menus, Exception ex);
    }

    private LocalDate date;
    private AllMenusListener listener;
    private Exception exception;

    public GetAllDiningMenusTask(LocalDate date) {
        this.date = date;
    }

    public GetAllDiningMenusTask(LocalDate date, AllMenusListener listener) {
        this(date);
        this.listener = listener;
    }

    public Exception getException() {
        return this.exception;
    }

    @Override
    protected List<DailyMenu> doInBackground(Void... params) {

        List<DailyMenu> menus = new ArrayList<DailyMenu>();

        for (DiningLocationName locationName : DiningLocationName.values()) {

            GetDailyDiningMenuTask task = new GetDailyDiningMenuTask(date, locationName);

            // This is execute synchronously because we are already on a background thread
            DailyMenu menu = task.doInBackground();

            // Check if an error occurred
            // Ignore the lint warning here. We don't want to throw exceptions in async tasks
            // because they'd immediately propagate to the java runtime, print out to the console,
            // and we'd never know if anything actually went wrong.
            if (task.getException() != null) {
                this.exception = task.getException();
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
            listener.onAllMenusResult(dailyMenus, exception);
        }
    }

}
