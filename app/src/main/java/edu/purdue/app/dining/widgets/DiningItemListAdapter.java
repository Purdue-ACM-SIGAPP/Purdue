package edu.purdue.app.dining.widgets;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import edu.purdue.app.R;
import edu.purdue.app.dining.models.FoodItem;
import edu.purdue.app.dining.models.Meal;
import edu.purdue.app.dining.models.Station;

/**
 * Created by mike on 2/7/15.
 */
public class DiningItemListAdapter extends BaseAdapter {

    private Context context;
    private Meal meal;
    private List<DiningItemListItem> serialized;

    public DiningItemListAdapter(Context context, Meal meal) {
        this.context = context;
        this.meal = meal;

        // Right now the list is essentially 2 dimensional, with stations then meals
        // We serialize this list here so that we only have 1 dimension using classes that
        // are private to this class.
        serialized = new ArrayList<>();
        for (Station station : meal.getStations()) {
            serialized.add(new DiningItemListItem(station.getName(), true));
            for (FoodItem item : station.getItems()) {
                serialized.add(new DiningItemListItem(item.getName(), false));
            }
        }

    }

    @Override
    public int getCount() {
        return serialized.size();
    }

    @Override
    public Object getItem(int position) {
        return serialized.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (serialized.get(position).isStation) {
            convertView = LayoutInflater.from(context).inflate(R.layout.dining_menu_station, parent, false);
            ((TextView)convertView.findViewById(R.id.dining_menu_station_label)).setText(serialized.get(position).name);

        } else {
            convertView = LayoutInflater.from(context).inflate(R.layout.dining_menu_item, parent, false);
            ((TextView)convertView.findViewById(R.id.dining_menu_item_label)).setText(serialized.get(position).name);
        }

        return convertView;
    }

    private class DiningItemListItem {
        String name;
        boolean isStation;

        public DiningItemListItem(String name, boolean isStation) {
            this.name = name;
            this.isStation = isStation;
        }
    }

}
