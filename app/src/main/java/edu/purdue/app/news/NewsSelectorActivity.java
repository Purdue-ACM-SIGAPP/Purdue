package edu.purdue.app.news;

/**
 * Feed selection activity
 *
 * @author hughe127
 */

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import edu.purdue.app.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class NewsSelectorActivity extends Activity {

    static int url;

    final static private String[] feedURLs = {"http://www.purdue.edu/newsroom/rss/academics.xml",
            "http://www.purdue.edu/newsroom/rss/AgriNews.xml",
            "http://www.purdue.edu/newsroom/rss/BizNews.xml",
            "http://www.purdue.edu/newsroom/rss/community.xml",
            "http://www.purdue.edu/newsroom/rss/DiversityNews.xml",
            "http://www.purdue.edu/newsroom/rss/EdCareerNews.xml",
            "http://www.purdue.edu/newsroom/rss/EventNews.xml",
            "http://www.purdue.edu/newsroom/rss/FeaturedNews.xml",
            "http://www.purdue.edu/newsroom/rss/general.xml",
            "http://www.purdue.edu/newsroom/rss/HealthMedNews.xml",
            "http://www.purdue.edu/newsroom/rss/InfoTech.xml",
            "http://www.purdue.edu/newsroom/rss/LifeNews.xml",
            "http://www.purdue.edu/newsroom/rss/LifeSciNews.xml",
            "http://www.purdue.edu/newsroom/rss/OTCNews.xml",
            "http://www.purdue.edu/newsroom/rss/outreach.xml",
            "http://www.purdue.edu/newsroom/rss/PhysicalSciNews.xml",
            "http://www.purdue.edu/newsroom/rss/ResearchNews.xml",
            "http://www.purdue.edu/newsroom/rss/StudentNews.xml",
            "http://www.purdue.edu/newsroom/rss/VetMedNews.xml"};

    private boolean ready = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        final ListView listview = (ListView) findViewById(R.id.listView);

        String[] feeds = {"Academic", "Agriculture", "Business",
                "Community", "Diversity", "Education and Career", "Events", "Featured",
                "General", "Health and Medicine", "IT", "Lifestyle", "Life Sciences", "Technology Commercialization",
                "Outreach", "Physical Sciences", "Research Foundation", "Student", "Veterinary Medicine"};

        final ArrayList<String> list = new ArrayList<String>();

        for (int i = 0; i < feeds.length; ++i) {
            list.add(feeds[i]);
        }

        if (getActionBar() != null) {
            getActionBar().setHomeButtonEnabled(true); // disable the button
            getActionBar().setDisplayHomeAsUpEnabled(true); // remove the left caret
            getActionBar().setDisplayShowHomeEnabled(true); // remove the icon
        }

        final StableArrayAdapter adapter = new StableArrayAdapter(this, android.R.layout.simple_list_item_1, list);
        listview.setAdapter(adapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
                final int item = position;
                url = item;
                ready = true;
                startActivity();
            }
        });

    }

    private class StableArrayAdapter extends ArrayAdapter<String> {

        HashMap<String, Integer> mIdMap = new HashMap<String, Integer>();

        public StableArrayAdapter(Context context, int textViewResourceId, List<String> objects) {
            super(context, textViewResourceId, objects);
            for (int i = 0; i < objects.size(); ++i) {
                mIdMap.put(objects.get(i), i);
            }
        }

        @Override
        public long getItemId(int position) {
            String item = getItem(position);
            return mIdMap.get(item);
        }

    }

    public static String getURL() {
        return feedURLs[url];
    }

    public void startActivity() {
        Intent activity = new Intent(this, RSSActivity.class);
        startActivity(activity);
    }

    @Override
    public boolean onNavigateUp() {
        finish();
        return true;
    }

}