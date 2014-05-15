package edu.purdue.app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.widget.AdapterView.OnItemClickListener;


public class MainMenuActivity extends Activity implements OnItemClickListener, OnRearrangeListener {

    private DraggableGridView dgv;
    private HashMap<Integer, MainMenuItem> gridItems; //Key is the view ID. Access with view.getID()

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        dgv = ((DraggableGridView) findViewById(R.id.draggable_grid_view));
        dgv.setOnItemClickListener(this);

        gridItems = new HashMap<Integer, MainMenuItem>();
        List<MainMenuItem> menuItems = MainMenuItem.getDefaultMainMenuItems(this.getResources());
        for(int i = 0; i < menuItems.size(); i++) {
            gridItems.put(i, menuItems.get(i));
        }

        setWidgets();
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

    private void setWidgets() {

        // Create all of the widgets that will display on the screen
        for(Map.Entry<Integer, MainMenuItem> entry : gridItems.entrySet()) {
            ImageView bu = new ImageView(this);
            bu.setImageDrawable(entry.getValue().icon);
            bu.setLayoutParams(new ViewGroup.LayoutParams(75, 75));
            bu.setFocusable(false);
            bu.setId(entry.getKey());
            dgv.addView(bu);
        }
    }

    @Override
    public void onRearrange(int oldIndex, int newIndex) {

    }

    @Override
    public void onPause()
    {
        super.onPause();
        SharedPreferences prefs = getSharedPreferences("grid_state", Context.MODE_PRIVATE);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        String url = gridItems.get(view.getId()).url;
        Intent webViewIntent = new Intent(getBaseContext(), WebViewActivity.class);
        webViewIntent.putExtra("URL_ENDPOINT", url);
        startActivity(webViewIntent);
    }
}
