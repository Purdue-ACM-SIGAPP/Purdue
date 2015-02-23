package edu.purdue.app.weather;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import edu.purdue.app.R;

/**
 * Created by mike on 2/23/15.
 */
public class WeatherActivity extends Activity {

    protected Fragment weatherForecastFragment = new WeatherForecastFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        //Inflate Weather Fragment
        getFragmentManager().beginTransaction().add(R.id.container, weatherForecastFragment).commit();

        Refresh.JSONWeatherTask task = new Refresh.JSONWeatherTask();
        task.execute();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu__weather, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //Switch to preference fragment
        if (item.getItemId() == R.id.settings_menu) {
            //getFragmentManager().beginTransaction().replace(R.id.container, new SettingsActivity()).commit();
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

}
