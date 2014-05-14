package edu.purdue.app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.askerov.dynamicgid.BaseDynamicGridAdapter;

import java.util.List;

/**
 * Created by david on 5/14/14 for Purdue
 */
public class CustomGridAdapter<T extends CustomMenuItem> extends BaseDynamicGridAdapter<T> {

    public CustomGridAdapter(Context context, List<T> items, int columnCount) {
        super(context, items, columnCount);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CustomViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_grid, null);
            holder = new CustomViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (CustomViewHolder) convertView.getTag();
        }
        holder.build(getItem(position));
        return convertView;
    }

    private class CustomViewHolder {
        private TextView titleText;
        private ImageView image;

        private CustomViewHolder(View view) {
            titleText = (TextView) view.findViewById(R.id.item_title);
            image = (ImageView) view.findViewById(R.id.item_img);
        }

        void build(CustomMenuItem item) {
            titleText.setText(item.getName());
            image.setImageDrawable(item.getDrawable());
        }
    }
}