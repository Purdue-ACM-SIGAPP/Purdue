package edu.purdue.app.dining.listeners;

import java.util.List;

import edu.purdue.app.dining.models.DailyMenu;

/**
 * Created by mike on 2/5/15.
 */
public interface DailyMenusListener {
    public void onGetDailyMenus(List<DailyMenu> menus, Exception ex);
}
