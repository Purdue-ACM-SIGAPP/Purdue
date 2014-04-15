package edu.purdue.app;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class MainMenuActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        DraggableGridView dgv = ((DraggableGridView) findViewById(R.id.draggable_grid_view));

        TextView tv1 = new TextView(this);
        tv1.setText("one");
        dgv.addView(tv1);

        TextView tv2 = new TextView(this);
        tv2.setText("two");
        dgv.addView(tv2);

        TextView tv3 = new TextView(this);
        tv3.setText("three");
        dgv.addView(tv3);

        TextView tv4 = new TextView(this);
        tv4.setText("four");
        dgv.addView(tv4);

        TextView tv5 = new TextView(this);
        tv5.setText("five");
        dgv.addView(tv5);
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

}
