package edu.purdue.app.activities;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;

import org.askerov.dynamicgid.DynamicGridView;
import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import edu.purdue.app.R;
import edu.purdue.app.mainmenu.MainMenuDynamicGridAdapter;
import edu.purdue.app.dialogs.NoInternetDialog;
import edu.purdue.app.mainmenu.MainMenuItem;
import edu.purdue.app.util.Analytics;
import edu.purdue.app.util.Connectivity;

import static android.widget.AdapterView.OnItemClickListener;


public class MainMenuActivity extends Activity implements OnItemClickListener {

    private static final String PREF_PAGE_GRID = "grid_locations";
    private static final String PREF_GRID_ITEMS = "grid_items";
    List<MainMenuItem> menuItems;
    private DynamicGridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        // Register a view to this screen on analytics
        Analytics.sendScreenView(Analytics.MAIN_SCREEN);

        // Set the drawable for the action bar
        ActionBar actionBar = getActionBar();
        if (actionBar != null) {
            actionBar.setIcon(R.drawable.ic_p);
        }

        // Display an error if there is no connectivity to the internet
        if(!Connectivity.isOnline(this)) {
            NoInternetDialog dialog = new NoInternetDialog(this);
            dialog.show();
        }

        // Get an instance of the draggable grid view we are using
        // and create our adapter for it
        gridView = (DynamicGridView) findViewById(R.id.dynamic_grid);
        MainMenuDynamicGridAdapter adapter = prepareAdapter();
        gridView.setAdapter(adapter);

        assignGridListeners();



    }

    private MainMenuDynamicGridAdapter prepareAdapter() {

        menuItems = MainMenuItem.getDefaultMainMenuItems(this);
        MainMenuDynamicGridAdapter ada = new MainMenuDynamicGridAdapter(this, new ArrayList<MainMenuItem>(menuItems), 4);

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

                while(menuItems.size() > items.size())
                    items.add(items.size());

            } catch (JSONException e) {
                Log.e("MainMenuActivity", "JSON from Shared Prefs was not valid", e);
            }

            if(items != null)
                ada.setLocations(items);
        }
        return ada;
    }

    private void assignGridListeners() {
//        gridView.setOnDropListener(new DynamicGridView.OnDropListener() {
//            @Override
//            public void onActionDrop() {
//            saveGridState();
//            gridView.stopEditMode();
//            }
//        });
//
//        gridView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
//            @Override
//            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
//            gridView.startEditMode();
//            return false;
//            }
//        });
        gridView.setOnItemClickListener(this);
    }

//    private void saveGridState() {
//        BaseDynamicGridAdapter dga = (BaseDynamicGridAdapter) gridView.getAdapter();
//        ArrayList<Integer> items = dga.getLocations();
//
//        JSONArray jarr = new JSONArray(items);
//        SharedPreferences prefs = getSharedPreferences(PREF_PAGE_GRID, Context.MODE_PRIVATE);
//        prefs.edit().putString(PREF_GRID_ITEMS, jarr.toString()).apply();
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //TODO: Add stuff to the menu
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
            Intent settings = new Intent(this, SettingsActivity.class);
            startActivity(settings);
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

        String url, name;
        startActivity(menuItems.get(i).getIntent());

        /*
        if(o instanceof ActivityMenuItem) {

            Intent intent = new Intent(this, ((ActivityMenuItem)o).getActivity());
            startActivity(intent);

        } else if (o instanceof WebViewMenuItem) {
            WebViewMenuItem item = (WebViewMenuItem) o;
            url = item.getUrl();
            name = item.getName();


            if (Connectivity.isOnline(this)) {

                Tracking.sendEvent(this, "ui_interaction", "grid_item_click", name);
                Log.d("GridItemClicked", "Opening url: " + url);
                Intent webViewIntent = new Intent(getBaseContext(), WebViewActivity.class);
                webViewIntent.putExtra(WebViewActivity.EXTRA_URL, url);
                webViewIntent.putExtra(WebViewActivity.EXTRA_NAME, name);
                startActivity(webViewIntent);

            } else {
                Tracking.sendEvent(this, "access_action", "grid_item_click", "no_internet");
                Toast.makeText(this, "No internet connection. Connect and try again.", Toast.LENGTH_LONG).show();
            }
        }
        */
    }

}
