package edu.purdue.app.data;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import org.joda.time.Duration;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

/**
 *  Class which implements persistent caching functionality for the rest of purdue app
 *  through storing gson representations of objects in sharedpreferences.
 *
 *  Created by mike@hockerman.com
 */
public class GsonCache implements Cache.Framework {

    private static final String SP_NAME = "gsoncache";
    private static final String SPK_COLKEYS = "gsoncache-keys/";
    private static final String SPK_ROOT = "gsoncache/";

    private Gson gson;
    private SharedPreferences sprefs;

    public GsonCache(Context context) {
        this.gson = new Gson();
        this.sprefs = context.getSharedPreferences(SP_NAME, 0);
    }

    private String key(String collection, String id) {
        return SPK_ROOT + collection + "/" + id;
    }

    private String keySet(String collection) {
        return SPK_COLKEYS + collection;
    }

    /** Stores an object in the caching framework under a given collection.
     *  @return the id of the object being stored.
     *  Either the id or the collection name is used later for querying. */
    @Override
    public String store(String collection, Object o) {
        SharedPreferences.Editor editor = sprefs.edit();

        // Generate an id for the object
        String id = UUID.randomUUID().toString();

        // Store the key under the keys set for this collection
        Set<String> keys = sprefs.getStringSet(keySet(collection), new HashSet<String>());
        Set<String> newKeys = new HashSet<String>(keys);
        newKeys.add(id);
        editor.putStringSet(keySet(collection), newKeys);

        // Store the object under the collection
        String json = gson.toJson(o);
        editor.putString(key(collection, id), json);

        editor.commit();
        return id;
    }

    /** ttl is not currently implemented */
    @Override
    public String cache(String collection, Object o, Duration ttl) {
        return store(collection, o);
    }

    @Override
    public <T> T get(String collection, String id, Class<T> c) {

        String json = sprefs.getString(key(collection, id), "");
        if (json.equals("")) {
            return null;
        }

        T result = gson.fromJson(json, c);

        return result;
    }

    @Override
    public <T> Set<T> getAll(String collection, Class<T> c) {

        Set<String> keys = sprefs.getStringSet(keySet(collection), new HashSet<String>());
        Set<T> objects = new HashSet<T>();

        for (String key : keys) {
            objects.add(get(collection, key, c));
        }

        return objects;
    }

    @Override
    public boolean delete(String collection, String id) {

        sprefs.edit().remove(key(collection, id)).apply();

        // Remove the key from the list of keys for this collection
        Set<String> keys = sprefs.getStringSet(keySet(collection), new HashSet<String>());
        if (keys.size() == 0) {
            return false;
        }
        Set<String> newKeys = new HashSet<String>(keys);
        keys.remove(id);
        sprefs.edit().putStringSet(keySet(collection), newKeys).apply();

        return true;
        
    }

    @Override
    public boolean deleteAll(String collection) {

        Set<String> ids = sprefs.getStringSet(keySet(collection), new HashSet<String>());
        for (String id : ids) {
            delete(collection, id);
        }

        sprefs.edit().remove(keySet(collection)).apply();

        return true;

    }
}
