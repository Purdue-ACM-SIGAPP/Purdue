package edu.purdue.app.dining.fragments;

import android.view.View;
import android.widget.AdapterView;

import org.joda.time.LocalDate;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

import edu.purdue.app.dining.data.DiningData;
import edu.purdue.app.dining.listeners.DailyMenusListener;
import edu.purdue.app.dining.listeners.MealTimesListener;
import edu.purdue.app.listeners.OnLoadedListener;
import edu.purdue.app.dining.models.DailyMenu;
import edu.purdue.app.dining.models.Meal;
import edu.purdue.app.fragments.MultiSelectCardListFragment;
import edu.purdue.app.widgets.CardViewListAdapter;

/**
 * Created by mike on 2/5/15.
 */
public class DiningTimesFragment extends MultiSelectCardListFragment implements MealTimesListener {

    private AdapterView.OnItemClickListener clickListener;
    private OnLoadedListener loadedListener;

    public void beginLoad() {
        // Get the list of times
        DiningData data = new DiningData();
        data.getMealTimes(LocalDate.now(), this);
    }

    @Override
    public void onGetTimes(List<String> times, Exception ex) {

        // Create and set the adapter
        CardViewListAdapter adapter = new CardViewListAdapter(getActivity(), times);
        gridView.setAdapter(adapter);

        // Alert that we're loaded
        loadedListener.onLoaded(this.getClass());

    }

    public void setOnLoadedListener(OnLoadedListener loadedListener) {
        this.loadedListener = loadedListener;
    }

    public void setOnItemClickListener(AdapterView.OnItemClickListener clickListener) {
        this.clickListener = clickListener;
    }

    /** Just like the locations fragment. We act as a proxy in this class by intercepting the item click,
     *  calling through to super, then re-calling the item click listener which the activity sets. */
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        super.onItemClick(parent, view, position, id);
        this.clickListener.onItemClick(parent, view, position, id);
    }

}
