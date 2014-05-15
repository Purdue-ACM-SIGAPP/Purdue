package org.askerov.dynamicgid;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: alex askerov
 * Date: 9/7/13
 * Time: 10:49 PM
 */
public abstract class BaseDynamicGridAdapter<T> extends AbstractDynamicGridAdapter {
    private Context mContext;

    private ArrayList<T> mItems = new ArrayList<T>();
    private int mColumnCount;

    private ArrayList<Integer> locations = new ArrayList<Integer>();

    protected BaseDynamicGridAdapter(Context context, int columnCount) {
        this.mContext = context;
        this.mColumnCount = columnCount;
    }

    public BaseDynamicGridAdapter(Context context, List<T> items, int columnCount) {
        mContext = context;
        mColumnCount = columnCount;
        init(items);
    }

    private void init(List<T> items) {
        addAllStableId(items);
        this.mItems.addAll(items);

        //fill the locations with default values.
        for(int i = 0; i < items.size(); i++) { locations.add(i); }
    }


    public void set(List<T> items) {
        clear();
        init(items);
        notifyDataSetChanged();
    }

    public void clear() {
        clearStableIdMap();
        mItems.clear();
        locations.clear();
        notifyDataSetChanged();
    }

    public void add(T item) {
        addStableId(item);
        locations.add(mItems.size());
        mItems.add(item);
        notifyDataSetChanged();
    }

    public void add(List<T> items) {
        addAllStableId(items);

        for(int i = 0; i < items.size(); i++)
            locations.add(mItems.size() + i);

        this.mItems.addAll(items);
        notifyDataSetChanged();
    }


//    public void remove(T item) {
//
//        mItems.remove(item);
//        removeStableID(item);
//        notifyDataSetChanged();
//        throw new RuntimeException("Remove not supported by this adapter. Must remove while retaining locations");
//    }

    @Override
    public int getCount() {
        return mItems.size();
    }

    public void setLocations(ArrayList<Integer> locations)
    {
        this.locations = locations;
    }

    public ArrayList<Integer> getLocations()
    {
        return locations;
    }

    @Override
    public T getItem(int position) {
        //Look up the position in the table, if there is a different item in
        // that location, use it. Else return the usual item.
        T item = mItems.get(locations.get(position));

        return item;
    }

    @Override
    public int getColumnCount() {
        return mColumnCount;
    }

    public void setColumnCount(int columnCount) {
        this.mColumnCount = columnCount;
        notifyDataSetChanged();
    }

    @Override
    public void reorderItems(int originalPosition, int newPosition) {
        if (newPosition < getCount()) {
            //DynamicGridUtils.reorder(mItems, originalPosition, newPosition);
            DynamicGridUtils.reorder(locations, originalPosition, newPosition);
            notifyDataSetChanged();
        }
    }

    public List getItems() {
        return mItems;
    }

    protected Context getContext() {
        return mContext;
    }
}
