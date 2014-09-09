package edu.purdue.app.labs.callbacks;


import edu.purdue.app.labs.datastore.model.Building;

/**
 * Created by david on 9/8/14.
 */
public interface OnGetBuildingCallback {
    public void onGetLatLngCallback(Building... locations);
}
