package edu.purdue.app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.askerov.dynamicgid.BaseDynamicGridAdapter;
import org.askerov.dynamicgid.DynamicGridView;
import org.json.JSONArray;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static android.widget.AdapterView.OnItemClickListener;


public class MainMenuActivity extends Activity implements OnItemClickListener {

    private DraggableGridView dgv;
    private HashMap<Integer, MainMenuItem> gridItems; //Key is the view ID. Access with view.getID()

    private DynamicGridView gridView;

    private String[] icons = new String[] { "Hello", "This", "is", "the", "New", "grid", "view"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        gridView = (DynamicGridView) findViewById(R.id.dynamic_grid);

        assignGridListeners();

        CustomGridAdapter adapter = prepareAdapter();
        gridView.setAdapter(adapter);

        prepTestButton();
    }

    private CustomGridAdapter prepareAdapter() {
        /*SharedPreferences prefs = getSharedPreferences("grid_locations", 0);
        if(prefs.contains("grid_items"))
        {
            String itemsJson = prefs.getString("grid_items", null);
            JSONArray jsonArray = null;
            List<String> items = null;
            try {
                jsonArray = new JSONArray(itemsJson);
                items = new ArrayList<String>();
                for (int i = 0; i < jsonArray.length(); i++)
                {
                    items.add(jsonArray.get(i).toString());
                }
            } catch (JSONException e) {
                Log.e("MainMenuActivity", "JSON from Shared Prefs was not valid");
            }

            return new CustomGridAdapter<MainMenuItem>(this,
                    new ArrayList<MainMenuItem>(items), 3);

        } else*/
        {
            gridItems = new HashMap<Integer, MainMenuItem>();
            List<MainMenuItem> menuItems = MainMenuItem.getDefaultMainMenuItems(this.getResources());
            for(int i = 0; i < menuItems.size(); i++) {
                gridItems.put(i, menuItems.get(i));
            }

            return new CustomGridAdapter<MainMenuItem>(this,
                    new ArrayList<MainMenuItem>(menuItems), 3);
        }
    }

    private void assignGridListeners() {
        gridView.setOnDropListener(new DynamicGridView.OnDropListener() {
            @Override
            public void onActionDrop() {
                saveGridState();

                gridView.stopEditMode();

                Toast.makeText(MainMenuActivity.this, "Item was dropped",
                        Toast.LENGTH_SHORT).show();
            }
        });

        gridView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                gridView.startEditMode();
                return false;
            }
        });

        gridView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainMenuActivity.this, parent.getAdapter().getItem(position).toString(),
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void prepTestButton() {
        // TODO: Below is a sample test of the web view. Should be removed later.
        Button testButton = ((Button) findViewById(R.id.webViewButton));
        final TextView testTextView = ((TextView) findViewById(R.id.urlTextField));
        testButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = testTextView.getText().toString();
                Intent webViewIntent = new Intent(getBaseContext(), WebViewActivity.class);
                webViewIntent.putExtra("URL_ENDPOINT", url);
                startActivity(webViewIntent);
            }
        });
    }

    private void saveGridState() {
        BaseDynamicGridAdapter dga = (BaseDynamicGridAdapter) gridView.getAdapter();
        List<String> items = dga.getItems();
        JSONArray jarr = new JSONArray(items);
        SharedPreferences prefs = getSharedPreferences("grid_locations", Context.MODE_PRIVATE);
        prefs.edit().putString("grid_items", jarr.toString()).apply();
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
        Toast.makeText(MainMenuActivity.this, adapterView.getAdapter().getItem(i).toString(),
                Toast.LENGTH_SHORT).show();

        /*String url = gridItems.get(view.getId()).url;
        Intent webViewIntent = new Intent(getBaseContext(), WebViewActivity.class);
        webViewIntent.putExtra("URL_ENDPOINT", url);
        startActivity(webViewIntent);*/
    }
}
