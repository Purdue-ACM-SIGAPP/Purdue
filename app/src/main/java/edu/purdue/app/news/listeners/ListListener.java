package edu.purdue.app.news.listeners;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

import edu.purdue.app.activities.WebViewActivity;
import edu.purdue.app.news.data.RssItem;


/**
 * Class implements a list listener
 *
 * @author hughe127
 *
 */
public class ListListener implements OnItemClickListener {

	// List item's reference
	List<RssItem> listItems;
	// Calling activity reference
	Context context;

	public ListListener(List<RssItem> aListItems, Context aContext) {
		listItems = aListItems;
		context  = aContext;
	}

	/**
	 * Start a browser with url from the rss item.
	 */
	public void onItemClick(AdapterView<?> parent, View view, int pos, long id) {
		Intent i = new Intent(context, WebViewActivity.class);
		i.setData(Uri.parse(listItems.get(pos).getLink()));
        i.putExtra(WebViewActivity.EXTRA_URL, i.getData().toString());
        i.putExtra(WebViewActivity.EXTRA_NAME, "News");
        context.startActivity(i);

	}
	
}
