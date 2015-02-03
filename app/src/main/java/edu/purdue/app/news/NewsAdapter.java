package edu.purdue.app.news;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import edu.purdue.app.R;

/** Adapter for News Activity page.
 * Created by silve_000 on 2/1/2015.
 */
public class NewsAdapter extends ArrayAdapter<String> {

    private final Activity context;
    private final ArrayList<String> web;
    private final Integer[] imageId;

    // Constructor for custom adapter; @param activity; @param String arraylist; @param int array
    public NewsAdapter(Activity context, ArrayList<String> web, Integer[] imageId) {

        super(context, R.layout.news_item_grid, web);
        this.context = context;
        this.web = web;
        this.imageId = imageId;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {

        LayoutInflater inflater = context.getLayoutInflater(); //Inflates activity

        //Prevents multiple inflations of the same layout for faster scrolling
        if (view == null) {
            view = inflater.inflate(R.layout.news_item_grid, null, false);
        }

        TextView txtTitle = (TextView) view.findViewById(R.id.txt); // Grabs title id from view
        ImageView imageView = (ImageView) view.findViewById(R.id.img); // Grabs icon id from view

        txtTitle.setText(web.get(position)); // Sets text in view to grabbed title
        imageView.setImageResource(imageId[position]); // Sets image in view to grabbed icon

        return view;
    }
}

