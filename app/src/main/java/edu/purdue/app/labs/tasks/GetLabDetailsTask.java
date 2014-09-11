package edu.purdue.app.labs.tasks;

import android.os.AsyncTask;
import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.purdue.app.labs.listeners.OnGetDetailsListener;
import edu.purdue.app.labs.model.Lab;

/**
 * Created by david on 9/10/14.
 */
public class GetLabDetailsTask extends AsyncTask<String, Void, Map<String, List<String>>> {

    OnGetDetailsListener listener;

    public GetLabDetailsTask(OnGetDetailsListener listener) {
        this.listener = listener;
    }

    @Override
    protected Map<String, List<String>> doInBackground(String... params) {

        String[] split = params[0].split(" ");
        String building = split[0];
        String room = split[1];

        //
        Document document;
        try {
            document = Jsoup.connect("https://lslab.ics.purdue.edu/icsWeb/LabInfo?building="+building.toUpperCase()+"&room=" + room).timeout(10*1000).get();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        return getDetailsFromDocument(document);
    }

    @Override
    protected void onPostExecute(Map<String, List<String>> stringListMap) {
        super.onPostExecute(stringListMap);
        listener.onGetDetails(stringListMap);
    }

    private Map<String, List<String>> getDetailsFromDocument(Document document) {
        Elements areaHeadings = document.select("div[class=area_heading]");

        Map<String, List<String>> data = new HashMap<String, List<String>>();

        for(int i = 0; i < 4; i++) {
            Element e = areaHeadings.get(i);
            String key = e.text();
            e = e.nextElementSibling();
            List<String> list = new ArrayList<String>();

            while(!e.tag().getName().equals("div") && !e.tag().getName().equals("p")) {
                Log.d("DETAILS", e.text());
                list.add(e.text());
                e = e.nextElementSibling().nextElementSibling();
            }
            data.put(key, list);
        }
        return data;

//        //Status
//        Element e = areaHeadings.get(0).nextElementSibling();
//        Log.d("DETAILS", e.text());
//        Log.d("DETAILS", e.nextElementSibling().nextElementSibling().text());
//        Log.d("DETAILS", e.nextElementSibling().nextElementSibling().nextElementSibling().nextElementSibling().text());
//
//        //Computers
//        e = areaHeadings.get(1).nextElementSibling();
//        while(!e.tag().getName().equals("div") && !e.tag().getName().equals("p")) {
//            Log.d("DETAILS", e.text());
//            e = e.nextElementSibling().nextElementSibling();
//        }
//
//        //Printers
//        e = areaHeadings.get(2).nextElementSibling();
//        while(!e.tag().getName().equals("div") && !e.tag().getName().equals("p")) {
//            Log.d("DETAILS", e.text());
//            e = e.nextElementSibling().nextElementSibling();
//        }
//
//        //Scanners
//        e = areaHeadings.get(3).nextElementSibling();
//        while(!e.tag().getName().equals("div") && !e.tag().getName().equals("p")) {
//            Log.d("DETAILS", e.text());
//            e = e.nextElementSibling().nextElementSibling();
//        }
    }
}
