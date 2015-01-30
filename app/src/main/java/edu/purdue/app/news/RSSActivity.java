package edu.purdue.app.news;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import edu.purdue.app.R;

/**
 * Main application activity.
 * 
 * @author hughe127
 *
 */
public class RSSActivity extends Activity {
	/** 
	 * This method creates activity_rss_display application view
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Set view
		setContentView(R.layout.activity_rss_display);

		super.onCreate(savedInstanceState);
		// Set view
		setContentView(R.layout.activity_rss_display);

        if (getActionBar() != null) {
            getActionBar().setHomeButtonEnabled(true); // disable the button
            getActionBar().setDisplayHomeAsUpEnabled(true); // remove the left caret
            getActionBar().setDisplayShowHomeEnabled(true); // remove the icon
        }

		try {
			// Get a ListView from activity_rss_display view
			ListView itcItems = (ListView) findViewById(R.id.listMainView);

			RssFeedTask rssFeedTask = new RssFeedTask(this, itcItems);

			rssFeedTask.execute();

		} catch (Exception e) {
			Log.e("Purdue RSS Reader", e.getMessage());
			e.printStackTrace();
		}
		
	}

    @Override
    public boolean onNavigateUp() {
        finish();
        return true;
    }
}