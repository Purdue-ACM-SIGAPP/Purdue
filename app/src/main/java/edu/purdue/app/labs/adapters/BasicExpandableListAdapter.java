package edu.purdue.app.labs.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import edu.purdue.app.R;
import edu.purdue.app.labs.fragments.LabDetailsFragment;

/**
 * Created by david on 9/10/14.
 */
public class BasicExpandableListAdapter extends BaseExpandableListAdapter {

    private Map<String, List<String>> mData;
    private List<String> mHeaders;
    private Context mContext;
    LayoutInflater inflater;


    public BasicExpandableListAdapter(Context context, Map<String, List<String>> data) {
        mData = data;
        mContext = context;
        mHeaders = new ArrayList<String>(mData.size());
        for(String s : mData.keySet()) {
            mHeaders.add(s);
        }

        Collections.sort(mHeaders);
    }

    private LayoutInflater getInflater() {
        if(inflater == null) {
            inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        return inflater;
    }

    @Override
    public int getGroupCount() {
        return mData.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return mData.get(mHeaders.get(groupPosition)).size();
    }

    @Override
    public String getGroup(int groupPosition) {
        return mHeaders.get(groupPosition);
    }

    @Override
    public String getChild(int groupPosition, int childPosition) {
        return mData.get(mHeaders.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return getGroup(groupPosition).hashCode();
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return getChild(groupPosition, childPosition).hashCode();
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        if(convertView == null) {
            convertView = getInflater().inflate(R.layout.labs_details_header, parent, false);
        }

        ((TextView)convertView.findViewById(R.id.header_text)).setText(getGroup(groupPosition));

        convertView.setClickable(false);
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView != null) {
            holder = (ViewHolder) convertView.getTag();
        } else {
            convertView = getInflater().inflate(android.R.layout.simple_list_item_1, parent, false);
            holder = new ViewHolder();

            holder.location = (TextView) convertView.findViewById(android.R.id.text1);

            convertView.setTag(holder);
        }
        holder.location.setText(getChild(groupPosition, childPosition));

        convertView.setClickable(false);
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    class ViewHolder {
        TextView location;
    }
}
