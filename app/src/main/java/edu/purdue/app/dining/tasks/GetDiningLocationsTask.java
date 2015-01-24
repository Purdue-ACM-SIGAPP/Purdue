package edu.purdue.app.dining.tasks;

import android.os.AsyncTask;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import edu.purdue.app.dining.models.Location;
import edu.purdue.app.util.Logger;

/**
 *  Task which returns a list of all the locations currently on the purdue /v2 api
 *  Created by mike on 1/22/15.
 */
public class GetDiningLocationsTask extends AsyncTask<Void, Void, List<Location>> {

    public interface GetDiningLocationsListener {
        public void onDiningLocationsResult(List<Location> locations, Exception ex);
    }

    private static final String url = "http://api.hfs.purdue.edu/menus/v2/locations/";

    private GetDiningLocationsListener listener;
    private Exception exception;

    public GetDiningLocationsTask() {}

    public GetDiningLocationsTask(GetDiningLocationsListener listener) {
        this.listener = listener;
    }

    @Override
    protected List<Location> doInBackground(Void... params) {

        try {

            // Object map the result
            ObjectMapper mapper = new ObjectMapper();
            LocationApiResult l = mapper.readValue(new URL(url), LocationApiResult.class);
            return l.getLocations();

        } catch (MalformedURLException ex) {
            Logger.e(ex);
            this.exception = ex;

        } catch (JsonParseException ex) {
            Logger.e(ex);
            this.exception = ex;

        } catch (JsonMappingException ex) {
            Logger.e(ex);
            this.exception = ex;

        } catch (IOException ex) {
            Logger.e(ex);
            this.exception = ex;

        }

        return null;
    }

    @Override
    protected void onPostExecute(List<Location> locations) {
        if (listener != null) {
            listener.onDiningLocationsResult(locations, exception);
        }
    }

    /** This is a private class which actually corresponds to the api the server will return.
     *  Essentially all it returns is a list of locations, but jackson isn't smart enough to
     *  just tell it that, so we need this class */
    private class LocationApiResult {

        @JsonProperty("Location")
        private List<Location> locations;

        public List<Location> getLocations() {
            return locations;
        }

        public void setLocations(List<Location> locations) {
            this.locations = locations;
        }

    }

}
