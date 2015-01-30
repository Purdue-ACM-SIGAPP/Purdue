package edu.purdue.app.news;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.AsyncTask;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import edu.purdue.app.R;
import edu.purdue.app.news.data.RssItem;
import edu.purdue.app.news.listeners.ListListener;
import edu.purdue.app.news.util.RssReader;

import java.util.List;

/**
 * Created by hughe127
 */

public class RssFeedTask extends AsyncTask<Void, Void, List<RssItem>> {

    private Context c;

    private ListView lv;

    public RssFeedTask(Context c, ListView lv) {
        this.lv = lv;
        this.c = c;
    }

    @Override
    protected List<RssItem> doInBackground(Void[] params) {

        RssReader rssReader = new RssReader(NewsSelectorActivity.getURL());

        try {
            return rssReader.getItems();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(List<RssItem> rssItemList) {
        ArrayAdapter<RssItem> adapter = new ArrayAdapter<RssItem>(c, android.R.layout.simple_list_item_1, rssItemList);
        lv.setAdapter(adapter);
        //lv.setOnItemClickListener(new ListListener(rssItemList, c));
        lv.setOnItemClickListener(new ListListener(rssItemList, c));
    }
}
