package edu.purdue.app.dining.listeners;

import java.util.List;

import edu.purdue.app.dining.models.Location;

/**
 * Created by mike on 2/5/15.
 */
public interface LocationsListener {
    public void onGetLocations(List<Location> locations, Exception ex);
}
