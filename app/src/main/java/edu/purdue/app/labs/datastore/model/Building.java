package edu.purdue.app.labs.datastore.model;

import com.google.android.gms.maps.model.LatLng;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by david on 9/8/14.
 */
public class Building {

    private String short_name;
    private String full_name;
    private double lat,lng;

    public LatLng getLatLng() {
        return new LatLng(lat, lng);
    }

    public String getShort_name() {
        return short_name;
    }

    public void setShort_name(String short_name) {
        this.short_name = short_name;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public static Building fromJson(JSONObject json) throws JSONException {
        Building b = new Building();
        b.setFull_name(json.getString("full_name"));
        b.setShort_name(json.getString("short_name"));
        b.setLat(json.getDouble("lat"));
        b.setLng(json.getDouble("lng"));
        return b;
    }
}
