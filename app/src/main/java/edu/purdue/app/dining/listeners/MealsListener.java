package edu.purdue.app.dining.listeners;

import java.util.List;

import edu.purdue.app.dining.models.Meal;

/**
 * Created by mike on 2/5/15.
 */
public interface MealsListener {
    public void onGetMenus(List<Meal> meals, Exception ex);
}
