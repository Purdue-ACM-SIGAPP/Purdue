package edu.purdue.app.news.tasks;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

import edu.purdue.app.news.activities.NewsCategoryActivity;
import edu.purdue.app.news.models.RssItem;
import edu.purdue.app.news.util.RssReader;

/**
 * Created by hughe127
 */

public class RssFeedTask extends AsyncTask<String, Void, List<RssItem>> {

    public interface RssFeedResultListener {
        public void onRssResult(List<RssItem> results, Exception ex);
    }

    private Context c;
    private RssFeedResultListener listener;

    // An exception which might get thrown during the parse process
    private Exception ex;

    public RssFeedTask(Context c) {
        this.c = c;
    }

    public RssFeedTask(Context c, RssFeedResultListener listener) {
        this.c = c;
        this.listener = listener;
    }

    @Override
    protected List<RssItem> doInBackground(String... params) {

        RssReader rssReader = new RssReader(params[0]);

        try {
            return rssReader.getItems();
        } catch (Exception ex) {
            this.ex = ex;
            return null;
        }

    }

    @Override
    protected void onPostExecute(List<RssItem> rssItemList) {
        listener.onRssResult(rssItemList, ex);
    }

}
