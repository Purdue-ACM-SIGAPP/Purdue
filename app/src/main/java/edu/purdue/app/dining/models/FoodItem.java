package edu.purdue.app.dining.models;

import org.codehaus.jackson.annotate.JsonProperty;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *  A single food item being served in a dining court
 *  Corresponds directly with the /v2 dining court API
 *  Created by mike on 1/22/15.
 */
public class FoodItem {

    public static FoodItem fromJson(JSONObject food) throws JSONException {
        FoodItem item = new FoodItem();
        item.id = food.getString("ID");
        item.name = food.getString("Name");
        item.isVegetarian = food.getBoolean("IsVegetarian");
        return item;
    }

    @JsonProperty("ID")
    private String id;

    @JsonProperty("Name")
    private String name;

    @JsonProperty("IsVegetarian")
    private boolean isVegetarian;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isVegetarian() {
        return isVegetarian;
    }

    public void setVegetarian(boolean isVegitarian) {
        this.isVegetarian = isVegitarian;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FoodItem foodItem = (FoodItem) o;

        if (isVegetarian != foodItem.isVegetarian) return false;
        if (id != null ? !id.equals(foodItem.id) : foodItem.id != null) return false;
        if (name != null ? !name.equals(foodItem.name) : foodItem.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (isVegetarian ? 1 : 0);
        return result;
    }

}
