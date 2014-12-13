package edu.purdue.app.labs.tasks;

import android.os.AsyncTask;
import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import edu.purdue.app.labs.listeners.OnGetAllLabsListener;

/**
 * Created by david on 9/8/14.
 */
public class GetAllItapTask extends AsyncTask<Void, Integer, List<String>> {

    private final OnGetAllLabsListener listener;

    public GetAllItapTask(OnGetAllLabsListener listener) {
        this.listener = listener;
    }

    @Override
    protected void onPostExecute(List<String> labs) {
        if (listener != null)
            listener.onGetAllLabs(labs);
    }

    @Override
    protected List<String> doInBackground(Void... params) {

        Document document;
        try {
            document = Jsoup.connect("https://lslab.ics.purdue.edu/icsWeb/LabSchedules").timeout(10*1000).get();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        return getLabsFromDocument(document);
    }

    private List<String> getLabsFromDocument(Document document) {
        if( document == null ) return null;
        Elements selects = document.select("select[name=labselect]");

        List<String> names = new LinkedList<String>();

        for(Element select : selects) {
            Elements options = select.children();
            for(Element option : options) {
                names.add(option.text());
            }
        }

        Log.d("LABS", "Found " + names.size() + " things");

        return names;
    }



}
