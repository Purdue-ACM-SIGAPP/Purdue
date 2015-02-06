package edu.purdue.app.listeners;

/**
 *  A generic interface for alerting one object that another object has finished loading.
 *  Created by mike on 2/5/15.
 */
public interface OnLoadedListener {
    public void onLoaded(Class from);
}
