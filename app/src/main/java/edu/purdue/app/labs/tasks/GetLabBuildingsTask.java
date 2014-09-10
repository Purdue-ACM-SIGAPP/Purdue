package edu.purdue.app.labs.tasks;

import android.os.AsyncTask;
import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import edu.purdue.app.labs.listeners.OnGetAvailableLabsListener;
import edu.purdue.app.labs.listeners.OnGetLabBuildingsListener;

/**
 * Created by david on 9/4/14.
 */
public class GetLabBuildingsTask extends AsyncTask<Void, Integer, Set<String>> {

    private final OnGetLabBuildingsListener listener;

    public GetLabBuildingsTask(OnGetLabBuildingsListener listener) {
        this.listener = listener;
    }

    @Override
    protected void onPostExecute(Set<String> labs) {
        if (listener != null)
            listener.onGetLabBuildings(labs);
    }

    @Override
    protected Set<String> doInBackground(Void... params) {

        Document document;
        try {
            document = Jsoup.connect("https://lslab.ics.purdue.edu/icsWeb/LabInfo").timeout(10*1000).get();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        return getLabsFromDocument(document);
    }

    private Set<String> getLabsFromDocument(Document document) {
        if( document == null ) return null;
        Elements select = document.select("select[name=bldg]");

        Set<String> names = new HashSet<String>();


        Collections.addAll(names, select.get(0).text().split(" "));

        Log.d("LABS", "Found " + names.size() + " things");

        return names;
    }



}
