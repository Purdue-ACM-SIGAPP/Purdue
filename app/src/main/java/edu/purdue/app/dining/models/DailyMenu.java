package edu.purdue.app.dining.models;

import org.codehaus.jackson.annotate.JsonProperty;
import org.joda.time.LocalDate;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 *  A single menu that is being served at a single food court for a single day
 *  This has 1:1 correspondence with the /v2 api
 *  Created by mike on 1/22/15.
 */
public class DailyMenu {

    public static DailyMenu fromJson(JSONObject object) throws JSONException {
        DailyMenu menu = new DailyMenu();
        menu.setLocation(object.getString("Location"));
        menu.setDateStr(object.getString("Date"));
        menu.setNotes(object.getString("Notes"));
        List<Meal> meals = new ArrayList<Meal>();
        JSONArray mealsAr = object.getJSONArray("Meals");
        for (int i = 0; i < mealsAr.length(); i++) {
            meals.add(Meal.fromJson(mealsAr.getJSONObject(i)));
        }
        menu.setMeals(meals);
        return menu;
    }

    @JsonProperty("Location")
    private String location;

    @JsonProperty("Date")
    private String dateStr;

    @JsonProperty("Notes")
    private String notes;

    @JsonProperty("Meals")
    private List<Meal> meals;

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDateStr() {
        return dateStr;
    }

    public void setDateStr(String dateStr) {
        this.dateStr = dateStr;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public List<Meal> getMeals() {
        return meals;
    }

    public void setMeals(List<Meal> meals) {
        this.meals = meals;
    }

    /** Returns a jodatime date object for this daily menu */
    public LocalDate getDate() {
        int month = Integer.parseInt(dateStr.split("/")[0]);
        int day = Integer.parseInt(dateStr.split("/")[1]);
        int year = Integer.parseInt(dateStr.split("/")[2]);
        LocalDate date = new LocalDate(year, month, day);
        return date;
    }

    @Override
    public String toString() {
        return "DailyMenu{" +
                "location='" + location + '\'' +
                ", dateStr='" + dateStr + '\'' +
                ", notes='" + notes + '\'' +
                ", meals=" + meals +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DailyMenu dailyMenu = (DailyMenu) o;

        if (dateStr != null ? !dateStr.equals(dailyMenu.dateStr) : dailyMenu.dateStr != null)
            return false;
        if (location != null ? !location.equals(dailyMenu.location) : dailyMenu.location != null)
            return false;
        if (meals != null ? !meals.equals(dailyMenu.meals) : dailyMenu.meals != null) return false;
        if (notes != null ? !notes.equals(dailyMenu.notes) : dailyMenu.notes != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = location != null ? location.hashCode() : 0;
        result = 31 * result + (dateStr != null ? dateStr.hashCode() : 0);
        result = 31 * result + (notes != null ? notes.hashCode() : 0);
        result = 31 * result + (meals != null ? meals.hashCode() : 0);
        return result;
    }

}
