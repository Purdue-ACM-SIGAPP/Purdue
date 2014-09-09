package edu.purdue.app.labs.datastore;

import android.content.Context;
import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

import edu.purdue.app.R;
import edu.purdue.app.labs.callbacks.OnGetBuildingCallback;
import edu.purdue.app.labs.datastore.model.Building;

public class BuildingsDataStore {

    public static void getLatLngForBuilding(final Context c, final OnGetBuildingCallback callback, String... buildings) {
        new AsyncTask<String, Void, Collection<Building>>() {

            //TODO: This method is terrible. I know this. I accept this. I expect Mike to change it soon.
            @Override
            protected Collection<Building> doInBackground(String... params) {
                Collection<Building> returnBuildings = new HashSet<Building>(params.length);
                for(JSONArray arr : getInstance(c).jsonArrays) {
                    for(int i = 0; i < arr.length(); i++) {
                        try {
                            JSONObject json = arr.getJSONObject(i);
                            if(Arrays.asList(params).contains(json.getString("short_name"))) {
                                returnBuildings.add(Building.fromJson(json));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
                
                return returnBuildings;
            }

            @Override
            protected void onPostExecute(Collection<Building> buildings) {
                super.onPostExecute(buildings);
                callback.onGetLatLngCallback(buildings.toArray(new Building[buildings.size()]));
            }
        }.execute(buildings);
    }

    private static BuildingsDataStore INSTANCE;

    private static BuildingsDataStore getInstance(Context c) {
        if(INSTANCE == null) {
            INSTANCE = new BuildingsDataStore(c);
            INSTANCE.init();
        }
        return INSTANCE;
    }

    private static final int[] BUILDINGS_FILE_RES = new int[] {
            R.raw.buildings_acad, R.raw.buildings_admin, R.raw.buildings_dining, R.raw.buildings_parking, R.raw.buildings_reshall
    };

    private HashSet<JSONArray> jsonArrays;

    private Context mContext;

    private BuildingsDataStore(Context c) {
        mContext = c;
        jsonArrays = new HashSet<JSONArray>(BUILDINGS_FILE_RES.length);
    }

    private void init() {
        for(int fileres : BUILDINGS_FILE_RES) {
            String data = decodeResource(fileres);
            try {
                JSONArray arr = new JSONArray(data);
                jsonArrays.add(arr);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    private String decodeResource(int fileres) {
        InputStream is = mContext.getResources().openRawResource(fileres);
        Writer writer = new StringWriter();
        char[] buffer = new char[1024];
        try {
            Reader reader = null;
            try {
                reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
                return null;
            }
            int n;
            while ((n = reader.read(buffer)) != -1) {
                writer.write(buffer, 0, n);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return writer.toString();
    }

}
