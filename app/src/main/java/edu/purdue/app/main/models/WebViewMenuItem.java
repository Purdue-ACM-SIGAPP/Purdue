package edu.purdue.app.main.models;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;

import edu.purdue.app.main.activities.WebViewActivity;

public class WebViewMenuItem extends MainMenuItem {

    public WebViewMenuItem(Context context, String name, Drawable drawable, String url) {
        super(name, drawable, createIntent(context, name, url));
    }

    /** This method call is necessary because java does not let you use a call to this() anywhere
     *  except the first statement of a constructor. fucking java. */
    private static Intent createIntent(Context context, String name, String url) {
        // Create an intent for this web view menu item to launch a webview activity
        Intent intent = new Intent(context, WebViewActivity.class);
        intent.putExtra(WebViewActivity.EXTRA_URL, url);
        intent.putExtra(WebViewActivity.EXTRA_NAME, name);
        return intent;
    }

}
