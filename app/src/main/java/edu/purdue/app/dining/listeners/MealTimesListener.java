package edu.purdue.app.dining.listeners;

import java.util.List;

/**
 * Created by mike on 2/6/15.
 */
public interface MealTimesListener {
    public void onGetTimes(List<String> times, Exception ex);
}
