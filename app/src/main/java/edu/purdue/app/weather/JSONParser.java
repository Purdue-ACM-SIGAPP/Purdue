package edu.purdue.app.weather;


import android.graphics.Bitmap;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Michael on 1/24/2015.
 */
public class JSONParser {
    protected static final String DEBUG_TAG = "Debug";
    static int result_all, sunrise, sunset, result_id;
    static double longitude, latitude, result_temp, result_pressure, result_humidity, result_temp_min, result_temp_max,
            result_speed, result_degree;
    static String country, weatherResult, result_wind, result_base, result_icon, result_description;
    private Bitmap iconData;

    public static String decodeJSON(String JSON) throws JSONException {
        String parsedResult = "";
        JSONObject jsonObject = new JSONObject(JSON);

        parsedResult += "Number of objects " + jsonObject.length() + "\n\n";
        /**
         * Coordinates
         */
        JSONObject jsonCoordinates = jsonObject.getJSONObject("coord");
        longitude = jsonCoordinates.getDouble("lon");
        latitude = jsonCoordinates.getDouble("lat");
        /**
         * Sys
         */
        JSONObject jsonSys = jsonObject.getJSONObject("sys");
        country = jsonSys.getString("country");
        sunrise = jsonSys.getInt("sunrise");
        sunset = jsonSys.getInt("sunset");
        /**
         * Weather
         */
        weatherResult = "";
        JSONArray arrayWeather = jsonObject.getJSONArray("weather");
        if (arrayWeather.length() > 0) {
            JSONObject jsonWeather = arrayWeather.getJSONObject(0);
            result_id = jsonWeather.getInt("id");
            String result_main = jsonWeather.getString("main");
            result_description = jsonWeather.getString("description");
            result_icon = jsonWeather.getString("icon");

            weatherResult = "weather\tid: " + result_id + "\tmain: " + result_main + "\tdescription: "
                    + result_description + "\ticon: " + result_icon;
        } else {
            Log.d(DEBUG_TAG, "weather array size is 0");
        }
        /**
         * Base
         */
        result_base = jsonObject.getString("base");
        /**
         * Main
         */
        JSONObject jsonMain = jsonObject.getJSONObject("main");
        result_temp = jsonMain.getDouble("temp");
        result_pressure = jsonMain.getDouble("pressure");
        result_humidity = jsonMain.getDouble("humidity");
        result_temp_min = jsonMain.getDouble("temp_min");
        result_temp_max = jsonMain.getDouble("temp_max");
        /**
         * Wind
         */
        JSONObject jsonWind = jsonObject.getJSONObject("wind");
        result_speed = jsonWind.getDouble("speed");
        result_degree = jsonWind.getDouble("deg");
        result_wind = "wind\tspeed: " + result_speed + "\tdegree: " + result_degree;
        /**
         * Cloud Coverage
         */
        JSONObject jsonClouds = jsonObject.getJSONObject("clouds");
        result_all = jsonClouds.getInt("all");


        return parsedResult;
    }

    public void setIconData(Bitmap iconData) {
        this.iconData = iconData;
    }

    public int getSunrise() {
        return sunrise;
    }

    public int getSunset() {
        return sunset;
    }

    public Bitmap getIconData() {
        return iconData;
    }

    public int getResult_temp_min() {
        return (int) result_temp_min;
    }

    public int getResult_temp_max() {
        return (int) result_temp_max;
    }

    public double getResult_temp() {
        return result_temp;
    }

    public String getResult_description() {
        return result_description;
    }

    public int getResult_pressure() {
        return (int) result_pressure;
    }

    public int getResult_speed() {
        return (int) result_speed;
    }

    public int getResult_degree() {
        return (int) result_degree;
    }

    public int getResult_humidity() {
        return (int) result_humidity;
    }

    public int getResult_all() {
        return result_all;
    }

    public int getResult_id() {
        return result_id;
    }

    public String getResult_icon() {
        return result_icon;
    }
}
