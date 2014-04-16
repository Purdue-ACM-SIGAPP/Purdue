package edu.purdue.app;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class MainMenuActivity extends Activity {

    private DraggableGridView dgv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        dgv = ((DraggableGridView) findViewById(R.id.draggable_grid_view));
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
        int NUM_BUTTONS = 9;
        for (int i = 0; i < NUM_BUTTONS; i++) {
            ImageView bu = new ImageView(this);
            bu.setImageDrawable(getResources().getDrawable(R.drawable.ic_launcher));
            bu.setLayoutParams(new ViewGroup.LayoutParams(75, 75));
            bu.setFocusable(false);
            dgv.addView(bu);
        }


    }

}
