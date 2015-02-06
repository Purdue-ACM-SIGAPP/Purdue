package edu.purdue.app.dining.tasks;

import android.os.AsyncTask;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.joda.time.LocalDate;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import edu.purdue.app.dining.listeners.DailyMenusListener;
import edu.purdue.app.dining.models.DailyMenu;
import edu.purdue.app.dining.data.DiningLocationName;
import edu.purdue.app.util.Logger;

/**
 *  Asynchronously returns a DailyMenu object for a specific day and location on campus
 *  Can optionally return the DailyMenu object through a callback inside this class
 *  Created by mike on 1/22/15.
 */
public class GetDailyDiningMenuTask extends AsyncTask<Void, Void, DailyMenu> {

    private LocalDate date;
    private DiningLocationName locationName;
    private DailyMenusListener listener;
    private Exception exception;

    public GetDailyDiningMenuTask(LocalDate date, DiningLocationName location) {
        this.date = date;
        this.locationName = location;
    }

    public GetDailyDiningMenuTask(LocalDate date, DiningLocationName locationName, DailyMenusListener listener) {
        this(date, locationName);
        this.listener = listener;
    }

    public Exception getException() {
        return this.exception;
    }

    @Override
    protected DailyMenu doInBackground(Void... params) {

        try {

            // Construct the URL
            URL url = locationName.createApiUrl(date);

            // Map to objects using jackson
            ObjectMapper mapper = new ObjectMapper();
            DailyMenu menu = mapper.readValue(url, DailyMenu.class);
            return menu;

        } catch (MalformedURLException ex) {
            Logger.e(ex);
            exception = ex;

        } catch (JsonParseException ex) {
            Logger.e(ex);
            exception = ex;

        } catch (JsonMappingException ex) {
            Logger.e(ex);
            exception = ex;

        } catch (IOException ex) {
            Logger.e(ex);
            exception = ex;

        }

        return null;

    }

    @Override
    protected void onPostExecute(DailyMenu menu) {
        if (listener != null) {
            List<DailyMenu> list = new ArrayList<>();
            list.add(menu);
            listener.onGetDailyMenus(list, exception);
        }
    }

}
