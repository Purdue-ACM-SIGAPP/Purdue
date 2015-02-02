package edu.purdue.app.news.widgets;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import edu.purdue.app.R;
import edu.purdue.app.news.data.DefaultNewsCategories;
import edu.purdue.app.util.Logger;

/** Adapter for News Activity page.
 *  Created by silve_000 on 2/1/2015.
 */
public class NewsCategoryItemAdapter extends BaseAdapter {

    private final Context context;

    public NewsCategoryItemAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return DefaultNewsCategories.values().length;
    }

    @Override
    public Object getItem(int position) {
        return DefaultNewsCategories.values()[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {

        // Prepare the requested item's name and image

        String categoryName = DefaultNewsCategories.values()[position].printable();
        int drawable = DefaultNewsCategories.values()[position].drawableResource();

        Logger.i(categoryName);
        Logger.i(drawable + "");

        // Create and return the view

        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.news_category, null, false);
        }

        TextView txtTitle = (TextView) view.findViewById(R.id.news_category_textview_categorytitle);
        ImageView imageView = (ImageView) view.findViewById(R.id.news_category_imageview);

        txtTitle.setText(categoryName);
        imageView.setImageResource(drawable);

        return view;

    }

}

