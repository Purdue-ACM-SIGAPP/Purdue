package edu.purdue.app.labs.tasks;

import android.os.AsyncTask;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import edu.purdue.app.labs.model.AvailableLab;

/**
 * Created by david on 9/4/14.
 */
public class GetLabBuildingsTask extends AsyncTask<Void, Integer, Set<String>> {
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
        Elements select = document.select("select[name=bldg]");

        Set<String> names = new HashSet<String>();

        for(Element e : select) {
            names.add(e.text());
        }

        return null;
    }

}
