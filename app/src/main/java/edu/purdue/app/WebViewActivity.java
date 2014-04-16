package edu.purdue.app;

import android.app.Activity;
import android.content.Intent;
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

        WebView webView = (WebView) findViewById(R.id.webView);
        webView.setWebViewClient(new WebViewClient());  // Set a web client so the links are loaded in we view
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);         // Javascript is not enabled by default, so enable it

        webView.loadUrl("http://mymail.purdue.edu/");
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
                // Implement Open page in browser
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(webView.getUrl()));
                startActivity(browserIntent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}
