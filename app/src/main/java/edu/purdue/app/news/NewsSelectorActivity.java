package edu.purdue.app.news;

/**
 * Feed selection activity
 *
 * @author hughe127
 * @author fieldn
 */

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ArrayAdapter;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import edu.purdue.app.R;

public class NewsSelectorActivity extends Activity {

    static int url;

    // Arraylist for names of categories
    final ArrayList<String> list = new ArrayList<String>() {{
        add("Academic"); add("Agriculture"); add("Business"); add("Community"); add("Diversity");
        add("Education and Career"); add("Events"); add("Featured"); add("General");
        add("Health and Medicine"); add("IT"); add("Lifestyle"); add("Life Sciences");
        add("Technology Commercialization"); add("Outreach"); add("Physical Sciences");
        add("Research Foundation"); add("Student"); add("Veterinary Medicine");
    }};
    // Int array for drawable icons
    Integer[] imageId = {
            R.drawable.news_ic_academic,
            R.drawable.news_ic_agriculture,
            R.drawable.news_ic_business,
            R.drawable.news_ic_community,
            R.drawable.news_ic_diversity,
            R.drawable.news_ic_edu_career,
            R.drawable.news_ic_events,
            R.drawable.news_ic_featured,
            R.drawable.news_ic_general,
            R.drawable.news_ic_health_medicine,
            R.drawable.news_ic_it,
            R.drawable.news_ic_life_sciences,
            R.drawable.news_ic_lifestyle,
            R.drawable.news_ic_outreach,
            R.drawable.news_ic_physical_sciences,
            R.drawable.news_ic_research,
            R.drawable.news_ic_student,
            R.drawable.news_ic_tech_comm,
            R.drawable.news_ic_vet_med
    };
    // String array of urls for each RSS news feed.
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
            "http://www.purdue.edu/newsroom/rss/VetMedNews.xml"
    };

    private boolean ready = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        NewsAdapter adapter = new NewsAdapter(NewsSelectorActivity.this, list, imageId);
        final GridView grid = (GridView)findViewById(R.id.gridview);
        grid.setAdapter(adapter);
        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
                    final int item = position;
                    url = item;
                    ready = true;
                    startActivity();
                }
            });
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