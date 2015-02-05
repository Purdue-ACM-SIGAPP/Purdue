package edu.purdue.app.widgets;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import edu.purdue.app.R;

/**
 *  This is an adapter for providing a simple list of strings on top of a card-based UI.
 *  Be warned that, compared to a simpler interface like an ArrayAdapter with a android.R.simplelistitem1,
 *  lists based on this adapter will take up WAY more vertical and horizontal room. Use it only when it makes
 *  sense, not everywhere.
 *  Created by mike on 2/4/15.
 */
public class CardViewListAdapter extends BaseAdapter {

    private Context context;
    private List<String> items;

    public CardViewListAdapter(Context context, List<String> items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.card_item, parent, false);
        }

        TextView tvTitle = (TextView) convertView.findViewById(R.id.card_item_textview);
        tvTitle.setText(items.get(position));

        return convertView;

    }

}
