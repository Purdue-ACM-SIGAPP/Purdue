package edu.purdue.app.dining.models;

import org.codehaus.jackson.annotate.JsonProperty;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 *  A meal is a collection of stations which serve fooditems at a specific time of day
 *  There is an implicit idea that these items are served on a specific day and at a specific place,
 *  but unfortunately this cannot be actualized in the model here because of the way the /v2 api is set up
 *
 *  Created by mike on 1/22/15.
 */
public class Meal {

    public static Meal fromJson(JSONObject obj) throws JSONException {
        Meal meal = new Meal();
        meal.setName(obj.getString("Name"));
        meal.setOrder(obj.getInt("Order"));
        meal.setHours(OpenHours.fromJson(obj.getJSONObject("Hours")));
        List<Station> stations = new ArrayList<Station>();
        JSONArray stationsAr = obj.getJSONArray("Stations");
        for (int i = 0; i < stationsAr.length(); i++) {
            stations.add(Station.fromJson(stationsAr.getJSONObject(i)));
        }
        meal.setStations(stations);
        return meal;
    }

    // Name is Breakfast, Lunch, Dinner, etc
    @JsonProperty("Name")
    private String name;

    @JsonProperty("Order")
    private int order;

    @JsonProperty("Hours")
    private OpenHours hours;

    @JsonProperty("Stations")
    private List<Station> stations;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public OpenHours getHours() {
        return hours;
    }

    public void setHours(OpenHours hours) {
        this.hours = hours;
    }

    public List<Station> getStations() {
        return stations;
    }

    public void setStations(List<Station> stations) {
        this.stations = stations;
    }

    @Override
    public String toString() {
        return "Meal{" +
                "name='" + name + '\'' +
                ", order=" + order +
                ", hours=" + hours +
                ", stations=" + stations +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Meal meal = (Meal) o;

        if (order != meal.order) return false;
        if (hours != null ? !hours.equals(meal.hours) : meal.hours != null) return false;
        if (name != null ? !name.equals(meal.name) : meal.name != null) return false;
        if (stations != null ? !stations.equals(meal.stations) : meal.stations != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + order;
        result = 31 * result + (hours != null ? hours.hashCode() : 0);
        result = 31 * result + (stations != null ? stations.hashCode() : 0);
        return result;
    }

}
