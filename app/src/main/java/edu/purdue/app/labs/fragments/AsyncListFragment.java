package edu.purdue.app.labs.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.view.View;
import android.widget.ListView;

import edu.purdue.app.R;

/**
 * Created by david on 9/4/14.
 */
public class AsyncListFragment extends Fragment {

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        showEmpty();
    }

    protected ListView getListView() {
        if(getView() != null)
            return (ListView) getView().findViewById(android.R.id.list);
        else return null;
    }

    protected void showError() {
        if(getView() == null) return;

        View list = getView().findViewById(android.R.id.list);
        View progress = getView().findViewById(android.R.id.empty);
        View error = getView().findViewById(R.id.errorView);

        list.setVisibility(View.GONE);
        progress.setVisibility(View.GONE);
        error.setVisibility(View.VISIBLE);
    }

    protected void showEmpty() {
        if(getView() == null) return;

        View list = getView().findViewById(android.R.id.list);
        View progress = getView().findViewById(android.R.id.empty);
        View error = getView().findViewById(R.id.errorView);

        list.setVisibility(View.GONE);
        progress.setVisibility(View.VISIBLE);
        error.setVisibility(View.GONE);
    }

    protected void showList() {
        if(getView() == null) return;

        View list = getView().findViewById(android.R.id.list);
        View progress = getView().findViewById(android.R.id.empty);
        View error = getView().findViewById(R.id.errorView);

        list.setVisibility(View.VISIBLE);
        progress.setVisibility(View.GONE);
        error.setVisibility(View.GONE);
    }
}
