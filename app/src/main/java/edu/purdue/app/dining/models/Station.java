package edu.purdue.app.dining.models;

import org.codehaus.jackson.annotate.JsonProperty;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 *  A single station in a dining court.
 *  This corresponds directly with the /v2 food court api.
 *  Created by mike on 1/22/15.
 */
public class Station {

    public static Station fromJson(JSONObject object) throws JSONException {
        Station station = new Station();
        station.setName(object.getString("Name"));
        List<FoodItem> items = new ArrayList<FoodItem>();
        JSONArray itemsAr = object.getJSONArray("Items");
        for (int i = 0; i < itemsAr.length(); i++) {
            items.add(FoodItem.fromJson(itemsAr.getJSONObject(i)));
        }
        station.setItems(items);
        return station;
    }

    @JsonProperty("Name")
    private String name;

    @JsonProperty("Items")
    private List<FoodItem> items;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<FoodItem> getItems() {
        return items;
    }

    public void setItems(List<FoodItem> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "Station{" +
                "name='" + name + '\'' +
                ", items=" + items +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Station station = (Station) o;

        if (items != null ? !items.equals(station.items) : station.items != null) return false;
        if (name != null ? !name.equals(station.name) : station.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (items != null ? items.hashCode() : 0);
        return result;
    }

}
