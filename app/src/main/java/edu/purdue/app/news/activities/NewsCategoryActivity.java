package edu.purdue.app.news.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import edu.purdue.app.R;
import edu.purdue.app.activities.WebViewActivity;
import edu.purdue.app.news.widgets.NewsCategoryItemAdapter;
import edu.purdue.app.news.data.DefaultNewsCategories;

public class NewsCategoryActivity extends Activity implements AdapterView.OnItemClickListener {

    private GridView categoryGrid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_categories);

        // Prepare our category list
        categoryGrid = (GridView) findViewById(R.id.news_category_gridview);

        NewsCategoryItemAdapter adapter = new NewsCategoryItemAdapter(this);
        categoryGrid.setAdapter(adapter);
        categoryGrid.setOnItemClickListener(this);
    }

    public void onItemClick(AdapterView<?> parent, View view, int pos, long id) {

        // Send the URL we are going to request along with the intent
        String url = DefaultNewsCategories.values()[pos].url();

        // Prepare an intent to launch the item
        Intent intent = new Intent(this, NewsStoryListActivity.class);

        // We use the EXTRA_URL from webview activity here just to save time
        intent.putExtra(WebViewActivity.EXTRA_URL, url);

        this.startActivity(intent);

    }

}