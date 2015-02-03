package edu.purdue.app.activities;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import edu.purdue.app.R;
import edu.purdue.app.util.Analytics;

/**
 *  Typical web view activity for any menu item which does not have
 *  native functionality implemented.
 */
public class WebViewActivity extends Activity {

    public static final String EXTRA_URL = "URL_ENDPOINT";
    public static final String EXTRA_NAME = "ITEM_NAME";

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        // The calling activity passes in the URL which will initially be displayed in the webview
        // as an intent extra.
        Bundle extras = getIntent().getExtras();
        if (extras == null) {
            throw new IllegalArgumentException("URL must be included when starting WebViewActivity");
        }
        String url = extras.getString(EXTRA_URL);
        final String name = extras.getString(EXTRA_NAME);

        // Set the title on the action bar
        ActionBar actionBar = getActionBar();
        if (actionBar != null) {
            actionBar.setTitle(name);
            actionBar.setIcon(R.drawable.ic_p);
        }

        // Register a view to this screen on our analytics
        Analytics.sendScreenView(Analytics.WEB_SCREEN, name);

        // Create the web view and restore its state if possible
        webView = (WebView) findViewById(R.id.webview_webview);
        if (savedInstanceState != null) {
            webView.restoreState(savedInstanceState);
        }

        // Enable javascript
        // Apparently this opens us up to XSS vulnerabilities, but we'll worry about that later
        webView.getSettings().setJavaScriptEnabled(true);

        // Create a webview client to time the pageload speed for analytics
        webView.setWebViewClient(new WebViewClient() {
            boolean firstLoad = true;
            long startTime;

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                invalidateOptionsMenu();
                //TODO: Time the first reload.
                if(firstLoad) {
                    startTime = System.currentTimeMillis();
                }
            }
            @Override
            public void onPageFinished(WebView view, String url) {
                invalidateOptionsMenu();

                if(firstLoad) {
                    firstLoad = false;
                    long time = System.currentTimeMillis() - startTime;
                    Analytics.sendTiming(WebViewActivity.this, "web_timing", "pageload", name, time);
                }
            }
        });

        // Load the actual URL
        if (url != null) {
            webView.loadUrl(url);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.web_view, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {

        if (webView.canGoForward()) {
            menu.findItem(R.id.web_actionbar_forward).setIcon(R.drawable.ic_action_forward);
        } else {
            menu.findItem(R.id.web_actionbar_forward).setIcon(R.drawable.ic_action_forward_disabled);
        }

        if (webView.canGoBack()) {
            menu.findItem(R.id.web_actionbar_back).setIcon(R.drawable.ic_action_back);
        } else {
            menu.findItem(R.id.web_actionbar_back).setIcon(R.drawable.ic_action_back_disabled);
        }
        return super.onPrepareOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.web_actionbar_refresh:
                webView.reload();
                break;
            case R.id.web_actionbar_browser:
                // Open page in browser
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(webView.getUrl()));
                startActivity(browserIntent);
                break;
            case R.id.web_actionbar_back:
                // Navigate back a page
                if (webView.canGoBack()) {
                    webView.goBack();
                }
                break;
            case R.id.web_actionbar_forward:
                // Navigate forward a page
                if (webView.canGoForward()) {
                    webView.goForward();
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }



    @Override
    public void onBackPressed() {
        WebView webView = (WebView) findViewById(R.id.webview_webview);

        if(webView.canGoBack()) {
            webView.goBack();
            return;
        }

        // Otherwise defer to system default behavior.
        super.onBackPressed();
    }

    @Override
    public boolean onNavigateUp() {
        finish();
        return true;
    }

}
