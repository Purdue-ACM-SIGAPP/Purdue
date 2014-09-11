package edu.purdue.app.labs.fragments;



import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.SimpleExpandableListAdapter;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

import edu.purdue.app.R;
import edu.purdue.app.labs.adapters.BasicExpandableListAdapter;
import edu.purdue.app.labs.listeners.OnGetDetailsListener;
import edu.purdue.app.labs.tasks.GetLabDetailsTask;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LabDetailsFragment#newInstance} factory method to
 * create an instance of this fragment.
 *
 */
public class LabDetailsFragment extends Fragment implements OnGetDetailsListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_LAB = "lab";

    // TODO: Rename and change types of parameters
    private String mLabName;

    private Map<String, List<String>> mData;
    private ExpandableListView mExpandableList;

    // TODO: Rename and change types and number of parameters
    public static LabDetailsFragment newInstance(String labName) {
        LabDetailsFragment fragment = new LabDetailsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_LAB, labName);
        fragment.setArguments(args);
        return fragment;
    }
    public LabDetailsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mLabName = getArguments().getString(ARG_LAB);
        }

        new GetLabDetailsTask(this).execute(mLabName);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.lab_fragment_lab_details, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mExpandableList = (ExpandableListView) view.findViewById(R.id.details_listview);
        ((TextView) view.findViewById(R.id.lab_title)).setText(mLabName);
        attemptPopulateView();
    }

    private void attemptPopulateView() {
        if(getView() == null) return;
        if(mData == null) return;
        Log.d("DETAILS", "Adding adapter");
        BasicExpandableListAdapter ada = new BasicExpandableListAdapter(this.getActivity(), mData);
        mExpandableList.setAdapter(ada);
        for(int i = 0; i < ada.getGroupCount(); i++) {
            mExpandableList.expandGroup(i);
        }
     }

    @Override
    public void onGetDetails(Map<String, List<String>> data) {
        mData = data;
        Log.d("DETAILS", "Obtained data!");
        for(String k : data.keySet()) {
            Log.d("DETAILS", k);
            for(String d : data.get(k)) {
                Log.d("DETAILS", "  " + d);
            }
        }
        attemptPopulateView();
    }


}
