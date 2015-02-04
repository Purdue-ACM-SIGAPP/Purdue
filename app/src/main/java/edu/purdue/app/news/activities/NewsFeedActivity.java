package edu.purdue.app.news.activities;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

import edu.purdue.app.R;
import edu.purdue.app.activities.WebViewActivity;
import edu.purdue.app.news.models.RssItem;
import edu.purdue.app.news.tasks.RssFeedTask;

/**
 * Application activity for the Rss categories view
 * 
 * @author hughe127
 */
public class NewsFeedActivity extends Activity implements
        RssFeedTask.RssFeedResultListener,
        AdapterView.OnItemClickListener {

    private ListView listView;
    private List<RssItem> results;

	@Override
	public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_news_feed);

        ActionBar actionBar = getActionBar();
        if (actionBar != null) {
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
        }

        // Get the URL we are going to be requesting to
        String url = this.getIntent().getStringExtra(WebViewActivity.EXTRA_URL);

        // Get the ListView from activity_news_feed view
        listView = (ListView) findViewById(R.id.news_feed_listview);

        // Download RSS data for the url
        RssFeedTask rssFeedTask = new RssFeedTask(this, this);
        rssFeedTask.execute(url);
		
	}

    @Override
    public void onRssResult(List<RssItem> results, Exception ex) {

        // Store the results for when they are clicked on later
        this.results = results;

        // inflate the adapter and display the results
        ArrayAdapter<RssItem> adapter = new ArrayAdapter<RssItem>(this, android.R.layout.simple_list_item_1, results);
        listView.setAdapter(adapter);

        // Set the on item click listener
        listView.setOnItemClickListener(this);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        // Obtain the correct result
        String resultName = results.get(position).getTitle();
        String resultUrl = results.get(position).getLink();

        // Start a webview activity
        Intent intent = new Intent(this, WebViewActivity.class);
        intent.putExtra(WebViewActivity.EXTRA_NAME, resultName);
        intent.putExtra(WebViewActivity.EXTRA_URL, resultUrl);

        startActivity(intent);

    }

}