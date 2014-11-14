package edu.purdue.app.labs.tasks;

import android.os.AsyncTask;
import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import edu.purdue.app.labs.listeners.OnGetAllLabsListener;

/**
 * Created by david on 11/14/14.
 */
public class GetAllECNTask extends AsyncTask<Void, Integer, List<String>> {

    private final OnGetAllECNListener listener;

    public GetAllECNTask(OnGetAllECNListener listener) {
        this.listener = listener;
    }

    @Override
    protected void onPostExecute(List<String> labs) {
        if (listener != null)
            listener.onGetAllECNLabs(labs);
    }

    @Override
    protected List<String> doInBackground(Void... params) {

        Document document;
        try {
            document = Jsoup.connect("https://engineering.purdue.edu/ECN/Services/Labs/engineering_labs.html").timeout(10*1000).get();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        return getLabsFromDocument(document);
    }

    private List<String> getLabsFromDocument(Document document) {
        if( document == null ) return null;
        Element table = document.select("table[summary=Engineering Computer Labs]").first();

        List<String> names = new LinkedList<String>();

        for(Element row : table.getElementsByTag("tbody").first().getElementsByTag("tr")) {

            names.add( row.children().first().text() );
        }

        Log.d("LABS", "Found " + names.size() + " things");

        return names;
    }



}
