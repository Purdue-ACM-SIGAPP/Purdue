package edu.purdue.app.dining.tasks;

import android.os.AsyncTask;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.joda.time.LocalDate;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import edu.purdue.app.dining.models.DailyMenu;
import edu.purdue.app.dining.util.LocationName;
import edu.purdue.app.util.Logger;

/**
 *  Asynchronously returns a DailyMenu object for a specific day and location on campus
 *  Can optionally return the DailyMenu object through a callback inside this class
 *  Created by mike on 1/22/15.
 */
public class GetDailyDiningMenuTask extends AsyncTask<Void, Void, DailyMenu> {

    /** Callback interface for this task */
    public interface DailyDiningMenuListener {
        public void onDailyMenuResult(DailyMenu menu, Exception ex);
    }

    private LocalDate date;
    private LocationName locationName;
    private DailyDiningMenuListener listener;
    private Exception exception;

    public GetDailyDiningMenuTask(LocalDate date, LocationName location) {
        this.date = date;
        this.locationName = location;
    }

    public GetDailyDiningMenuTask(LocalDate date, LocationName locationName, DailyDiningMenuListener listener) {
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
            listener.onDailyMenuResult(menu, exception);
        }
    }

}
