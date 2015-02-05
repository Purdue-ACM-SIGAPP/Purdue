package edu.purdue.app.dining.widgets;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import edu.purdue.app.R;

/**
 * Created by mike on 2/4/15.
 */
public class DiningMasterAdapter extends BaseAdapter {

    private Context context;
    private List<String> items;

    public DiningMasterAdapter(Context context, List<String> items) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.card_item_small, parent, false);
        }

        TextView tvTitle = (TextView) convertView.findViewById(R.id.card_item_small_textview);
        tvTitle.setText(items.get(position));

        return convertView;

    }

}
