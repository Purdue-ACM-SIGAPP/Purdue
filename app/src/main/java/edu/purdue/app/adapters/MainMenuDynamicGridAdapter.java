package edu.purdue.app.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.askerov.dynamicgid.BaseDynamicGridAdapter;

import java.util.List;

import edu.purdue.app.R;
import edu.purdue.app.mainmenu.MainMenuItem;

/**
 *  Despite the scary name, this is simply an adapter for main menu items.
 *  Created by david on 5/14/14 for Purdue
 */
public class MainMenuDynamicGridAdapter extends BaseDynamicGridAdapter<MainMenuItem> {

    public MainMenuDynamicGridAdapter(Context context, List<MainMenuItem> items, int columnCount) {
        super(context, items, columnCount);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // Get the item at the position we are accessing
        MainMenuItem item = getItem(position);

        // If cv is available, use it
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_grid, parent, false);
        }

        // Get the textview and image from the inflated view
        TextView label = (TextView) convertView.findViewById(R.id.item_title);
        ImageView imageView = (ImageView) convertView.findViewById(R.id.item_img);

        // Set the widgets appropriately
        label.setText(item.getName());
        imageView.setImageDrawable(item.getDrawable());

        return convertView;
    }

}