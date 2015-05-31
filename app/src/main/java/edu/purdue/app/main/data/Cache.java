package edu.purdue.app.main.data;

import android.content.Context;

import org.joda.time.Duration;

import java.util.Set;

/**
 * Created by mike on 1/23/15.
 */
public abstract class Cache {

    public interface Framework {
        public String store(String collection, Object o);
        public String cache(String collection, Object o, Duration ttl);
        public <T> T get(String collection, String id, Class<T> c);
        public <T> Set<T> getAll(String collection, Class<T> c);
        public boolean delete(String collection, String id);
        public boolean deleteAll(String collection);
    }

    public static Framework getDefaultCacheSystem(Context c) {
        return new GsonCache(c);
    }

}
