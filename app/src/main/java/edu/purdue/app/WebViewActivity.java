package edu.purdue.app;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;


public class WebViewActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        // Retrieve the url from the calling activity via the intent's extras bundle
        Bundle extras = getIntent().getExtras();
        String url = null;
        if (extras != null) {
            url = extras.getString("URL_ENDPOINT");
        }

        WebView webView = (WebView) findViewById(R.id.webView);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);         // Javascript is not enabled by default, so enable it
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                invalidateOptionsMenu();
            }
            @Override
            public void onPageFinished(WebView view, String url) {
                invalidateOptionsMenu();
            }
        });

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
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        WebView webView = (WebView) findViewById(R.id.webView);
        switch (item.getItemId()) {
            case R.id.web_actionbar_refresh:
                // Refresh the web page
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
    public boolean onPrepareOptionsMenu(Menu menu) {
        WebView webView = (WebView) findViewById(R.id.webView);

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
    public void onBackPressed() {
        WebView webView = (WebView) findViewById(R.id.webView);

        if(webView.canGoBack()) {
            webView.goBack();
            return;
        }

        // Otherwise defer to system default behavior.
        super.onBackPressed();
    }
}
