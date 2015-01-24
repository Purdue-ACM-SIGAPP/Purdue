package edu.purdue.app.util;

import android.util.Log;

/**
 * Created by mike on 1/22/15.
 */
public abstract class Logger {

    public static final String LOG_TAG_PREFIX = "purdue_app";

    public static void i(String message) {
        Log.i(LOG_TAG_PREFIX, message);
    }

    public static void i(String message, Class c) {
        Log.i(LOG_TAG_PREFIX + ":" + c.getName(), message);
    }

    public static void e(String message) {
        Log.e(LOG_TAG_PREFIX, message);
    }

    public static void e(Exception ex) {
        Log.e(LOG_TAG_PREFIX, ex.getLocalizedMessage());
    }

    public static void e(String message, Class c) {
        Log.e(LOG_TAG_PREFIX + ":" + c.getName(), message);
    }

    public static void e(String message, Exception ex) {
        e(message);
        e(ex);
    }

    public static void e(String message, Exception ex, Class c) {
        e(message, c);
        Log.e(LOG_TAG_PREFIX + ":" + c.getName(), ex.getLocalizedMessage());
    }

}
