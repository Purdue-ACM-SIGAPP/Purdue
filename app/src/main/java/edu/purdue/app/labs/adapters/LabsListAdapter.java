package edu.purdue.app.labs.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import edu.purdue.app.R;
import edu.purdue.app.labs.model.Lab;

/**
 * Created by david on 9/2/14.
 */
public class LabsListAdapter extends BaseExpandableListAdapter {
    List<String> headers;
    Map<Lab.Type, List<Lab>> labs;
    Context context;
    LayoutInflater inflater;

    public LabsListAdapter(Context context, List<Lab> labs) {
        this.context = context;
        this.labs = new HashMap<Lab.Type, List<Lab>>();
        this.headers = new ArrayList<Lab.Type>();

        for(Lab lab : labs) {


            Lab.Type t = lab.getType();
            List<Lab> list = this.labs.get(t);
            if(list == null) {
                list = new LinkedList<Lab>();
                this.headers.add(t);
            }
            list.add(lab);

            this.labs.put(t, list);
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
    public Lab.Type getGroup(int groupPosition) {
        return headers.get(groupPosition);
    }

    @Override
    public Lab getChild(int groupPosition, int childPosition) {
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

        ((TextView)convertView.findViewById(R.id.header_text)).setText(getGroup(groupPosition).toString());

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView != null) {
            holder = (ViewHolder) convertView.getTag();
        } else {
            convertView = getInflater().inflate(R.layout.labs_lab_listitem, parent, false);
            holder = new ViewHolder();
            holder.available = (TextView) convertView.findViewById(R.id.lab_txt_available);
            holder.location = (TextView) convertView.findViewById(R.id.lab_txt_location);
//            holder.colorBar = (View) convertView.findViewById(R.id.color_indicator);
            convertView.setTag(holder);
        }

        holder.available.setText( "" + getChild(groupPosition, childPosition).getAvailableStations() );
        holder.location.setText( getChild(groupPosition, childPosition).getLocation() );
        // holder.colorBar.setBackground(context.getResources().getDrawable(R.drawable.labs_color_bar));
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    @Override
    public boolean isEmpty() {
        return labs == null || labs.size() == 0;
    }

    class ViewHolder {
        TextView location;
        TextView available;
//        View colorBar;
    }
}
