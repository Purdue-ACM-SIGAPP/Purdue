package edu.purdue.app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;

import org.askerov.dynamicgid.BaseDynamicGridAdapter;
import org.askerov.dynamicgid.DynamicGridView;
import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import static android.widget.AdapterView.OnItemClickListener;


public class MainMenuActivity extends Activity implements OnItemClickListener {

    private static final String PREF_PAGE_GRID = "grid_locations";
    private static final String PREF_GRID_ITEMS = "grid_items";

    private DynamicGridView gridView;

    List<MainMenuItem> menuItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main_menu);

        gridView = (DynamicGridView) findViewById(R.id.dynamic_grid);

        CustomGridAdapter adapter = prepareAdapter();
        gridView.setAdapter(adapter);

        assignGridListeners();
    }

    private CustomGridAdapter prepareAdapter() {

        menuItems = MainMenuItem.getDefaultMainMenuItems(this.getResources());
        CustomGridAdapter ada = new CustomGridAdapter<MainMenuItem>(this,
                new ArrayList<MainMenuItem>(menuItems), 4);

        SharedPreferences prefs = getSharedPreferences(PREF_PAGE_GRID, 0);
        if(prefs.contains(PREF_GRID_ITEMS))
        {
            String itemsJson = prefs.getString(PREF_GRID_ITEMS, null);
            JSONArray jsonArray = null;
            ArrayList<Integer> items = null;
            try {
                jsonArray = new JSONArray(itemsJson);
                items = new ArrayList<Integer>();
                for (int i = 0; i < jsonArray.length(); i++)
                {
                    items.add(jsonArray.getInt(i));
                }
            } catch (JSONException e) {
                Log.e("MainMenuActivity", "JSON from Shared Prefs was not valid", e);
            }

            if(items != null)
                ada.setLocations(items);
        }
        return ada;
    }

    private void assignGridListeners() {
        gridView.setOnDropListener(new DynamicGridView.OnDropListener() {
            @Override
            public void onActionDrop() {
            saveGridState();
            gridView.stopEditMode();
            }
        });

        gridView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
            gridView.startEditMode();
            return false;
            }
        });

        gridView.setOnItemClickListener(this);
    }

    private void saveGridState() {
        BaseDynamicGridAdapter dga = (BaseDynamicGridAdapter) gridView.getAdapter();
        ArrayList<Integer> items = dga.getLocations();

        JSONArray jarr = new JSONArray(items);
        SharedPreferences prefs = getSharedPreferences(PREF_PAGE_GRID, Context.MODE_PRIVATE);
        prefs.edit().putString(PREF_GRID_ITEMS, jarr.toString()).apply();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (gridView.isEditMode()) {
            gridView.stopEditMode();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Object o = view.getTag();

        if(o instanceof MainMenuItem)
        {
            MainMenuItem item = (MainMenuItem) o;
            String url = item.getUrl();
            Log.d("GridItemClicked", "Opening url: " + url);
            Intent webViewIntent = new Intent(getBaseContext(), WebViewActivity.class);
            webViewIntent.putExtra(WebViewActivity.EXTRA_URL, url);
            startActivity(webViewIntent);
        }
    }
}
