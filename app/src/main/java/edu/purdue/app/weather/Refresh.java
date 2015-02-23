package edu.purdue.app.weather;

import android.util.Log;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import static edu.purdue.app.weather.ServerInput.downloadJSON;

/**
 * Created by Michael on 1/31/2015.
 */
public class Refresh {

    private static final String currentWeather = "http://api.openweathermap.org/data/2.5/weather?q=West%20Lafayette&units=imperial";
    private static String IMG_URL = "http://openweathermap.org/img/w/";
    private static byte[] byteArray;

    static class JSONWeatherTask extends AsyncTask<String, WeatherForecastFragment, JSONParser> {

        @Override
        protected JSONParser doInBackground(String... params) {
            downloadJSON(currentWeather);
            JSONParser parser = updateData();
            return parser;
        }

        @Override
        protected void onPostExecute(JSONParser parser) {
            updateUI(parser);
        }
    }

    protected static JSONParser updateData() {
        JSONParser parserClass = new JSONParser();
        GetImage imageDownloader = new GetImage();
        String RESULT_ICON_STRING = parserClass.getResult_icon();
        Bitmap weatherConditionIcon = imageDownloader.downloadImage(IMG_URL + RESULT_ICON_STRING + ".png");
        parserClass.setIconData(weatherConditionIcon);
        Log.d("Debug", "Image Bitmap URL: " + IMG_URL + RESULT_ICON_STRING + ".png");
        return parserClass;
    }

    protected static void updateUI(JSONParser parserClass) {
        WeatherForecastFragment weatherFragment = new WeatherForecastFragment();
        Bitmap weatherConditionIcon = parserClass.getIconData();
        Log.d("Debug", "Bitmap Null? :" + weatherConditionIcon);
        if (weatherConditionIcon != null) {
            Log.d("Debug", "Weather Icon Set");
            weatherFragment.imgView.setImageBitmap(weatherConditionIcon);
        }
        weatherFragment.minTemp.setText("" + parserClass.getResult_temp_min() + "℉");
        weatherFragment.maxTemp.setText("" + parserClass.getResult_temp_max() + "℉");
        weatherFragment.condDescr.setText("" + parserClass.getResult_description());
        weatherFragment.temp.setText("" + parserClass.getResult_temp());
        weatherFragment.unitTemp.setText("℉");
        weatherFragment.hum.setText("" + parserClass.getResult_humidity() + "%");
        weatherFragment.press.setText("" + parserClass.getResult_pressure() + "° HPA");
        weatherFragment.visibility.setText("" + parserClass.getResult_all() + "%");
        weatherFragment.windSpeed.setText("" + parserClass.getResult_speed() + " mph");
        weatherFragment.windDeg.setText("" + parserClass.getResult_degree() + "°");

        long time = parserClass.getSunrise() * (long) 1000;
        Date date = new Date(time);
        SimpleDateFormat format = new SimpleDateFormat("EEE MMM dd HH:mm:ss a yyyy");
        format.setTimeZone(TimeZone.getTimeZone("EST"));
        weatherFragment.sunrise.setText("" + format.format(date));

        time = parserClass.getSunset() * (long) 1000;
        date = new Date(time);
        format = new SimpleDateFormat("EEE MMM dd HH:mm:ss a yyyy");
        format.setTimeZone(TimeZone.getTimeZone("EST"));
        weatherFragment.sunset.setText("" + format.format(date));
    }
}
