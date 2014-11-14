package edu.purdue.app.labs.tasks;

import android.os.AsyncTask;
import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import edu.purdue.app.labs.listeners.OnGetAvailableLabsListener;
import edu.purdue.app.labs.model.Lab;

/**
 * Created by david on 9/2/14.
 */
public class GetAvailableItapTask extends AsyncTask<Void, Integer, List<Lab>> {

    private final OnGetAvailableLabsListener listener;

    public GetAvailableItapTask(OnGetAvailableLabsListener listener) {
        this.listener = listener;
    }

    @Override
    protected void onPostExecute(List<Lab> availableLabs) {
        if (listener != null)
            listener.onGetLabs(availableLabs);
    }

    @Override
    protected List<Lab> doInBackground(Void... params) {

        Document document;
        try {
            document = Jsoup.connect("https://lslab.ics.purdue.edu/icsWeb/AvailableStations").timeout(10*1000).get();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        return getLabsFromDocument(document);
    }

    private List<Lab> getLabsFromDocument(Document document) {
        List<Lab> labs = new ArrayList<Lab>();
        for (Element table : document.select("table")) {

            Lab.Type type = null;

            for (Element row : table.select("tr")) {
                Elements headers = row.select("th");
                if(headers.size() > 1) {
                    String typeName = headers.get(0).text();
                    if(typeName.toLowerCase().contains("mac")) {
                        type = Lab.Type.MAC;
                    } else {
                        type = Lab.Type.PC;
                    }
                } else {
                    Elements tds = row.select("td");

                    Lab lab = createLab(headers.get(0).text(), tds.get(0).text(), tds.get(1).text(), type);
                    labs.add(lab);
                }
            }
        }
        return labs;
    }

    private Lab createLab(String location, String availableStations, String openUntil, Lab.Type type) {
        Lab lab = new Lab();
        lab.setAvailableStations( Integer.parseInt(availableStations) );
        lab.setName(location);
        lab.setLocation(location);
        lab.setType(type);
        lab.setAvailableUntil(openUntil);
        return lab;
    }
}
