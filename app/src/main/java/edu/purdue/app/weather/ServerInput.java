package edu.purdue.app.weather;

import android.util.Log;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import static edu.purdue.app.weather.JSONParser.decodeJSON;

/**
 * Created by Michael on 1/26/2015.
 */
public class ServerInput {
    protected static final String DEBUG_TAG = "Debug";

    public static void downloadJSON(String openWeatherMapUrl) {
        String result = "";

        try {
            URL url_weather = new URL(openWeatherMapUrl);

            HttpURLConnection urlConnection = (HttpURLConnection) url_weather.openConnection();

            if (urlConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                InputStreamReader streamReader = new InputStreamReader(urlConnection.getInputStream());
                BufferedReader bufferedReader = new BufferedReader(streamReader);
                String line = null;
                while ((line = bufferedReader.readLine()) != null) {
                    result += line;
                }

                bufferedReader.close();

                String weatherResult = decodeJSON(result);

                Log.d("Verbose", "Weather Result: " + weatherResult);
            } else {
                Log.d(DEBUG_TAG, "Connection Returned false");
            }

        } catch (MalformedURLException mue) {
            Log.d(DEBUG_TAG, "Malformed URL Exception Thrown in Server Input");
        } catch (IOException io) {
            Log.d(DEBUG_TAG, "IO Exception Thrown in Server Input");
        } catch (JSONException json) {
            Log.d("DEBUG TAG", "JSON Exception thrown in Server Input");
        }
    }
}
