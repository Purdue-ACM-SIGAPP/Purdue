package edu.purdue.app.labs.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import edu.purdue.app.R;
import edu.purdue.app.labs.model.Lab;

/**
 * Created by david on 9/2/14.
 */
public class AllLabsListAdapter extends BaseExpandableListAdapter {
    List<String> headers;
    Map<String, List<String>> labs;
    Context context;
    LayoutInflater inflater;

    public AllLabsListAdapter(Context context, List<String> labs) {
        this.context = context;
        this.labs = new HashMap<String, List<String>>();
        this.headers = new ArrayList<String>();

        for(String lab : labs) {
            String location = lab.split(" ")[0];
            List<String> list = this.labs.get(location);
            if(list == null) {
                list = new LinkedList<String>();
                this.headers.add(location);
            }
            list.add(lab);

            this.labs.put(location, list);
        }
    }

    private LayoutInflater getInflater() {
        if(inflater == null) {
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        return inflater;
    }

    @Override
    public int getGroupCount() {
        return this.labs.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return this.labs.get(this.headers.get(groupPosition)).size();
    }

    @Override
    public String getGroup(int groupPosition) {
        return headers.get(groupPosition);
    }

    @Override
    public String getChild(int groupPosition, int childPosition) {
        return this.labs.get(this.headers.get(groupPosition)).get(childPosition);
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
            convertView = getInflater().inflate(R.layout.labs_lab_listheader, parent, false);
        }

        ((TextView)convertView.findViewById(R.id.header_text)).setText(getGroup(groupPosition));
        ((TextView)convertView.findViewById(R.id.lab_count)).setText("" + getChildrenCount(groupPosition));

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

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

    @Override
    public boolean isEmpty() {
        return labs == null || labs.size() == 0;
    }

    class ViewHolder {
        TextView location;
    }
}
