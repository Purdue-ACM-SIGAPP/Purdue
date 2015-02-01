package edu.purdue.app.dining.util;

import org.joda.time.LocalDate;

import java.net.MalformedURLException;
import java.net.URL;

/**
 *  Every dining court location that we currently recognize on campus.
 *  Ideally the functionality from this class should be provided by some call to
 *  /api/v2/locations, but considering how unoften dining court locations are
 *  updated and how much slower that would make the app this is probably a good
 *  compromise.
 *
 *  Created by mike on 1/22/15.
 */
public enum DiningLocationName {

    EARHART     ("Earhart",     "earhart"),
    FORD        ("Ford",        "ford"),
    HILLENBRAND ("Hillenbrand", "hillenbrand"),
    WILEY       ("Wiley",       "wiley"),
    WINDSOR     ("Windsor",     "windsor");

    private String printableName;
    private String urlComponent;

    DiningLocationName(String printable, String urlComponent) {
        this.printableName = printable;
        this.urlComponent = urlComponent;
    }

    public String printableName() {
        return printableName;
    }

    public String urlComponent() {
        return urlComponent;
    }

    /** Creates the url for a given date and the location you are trying to access */
    public URL createApiUrl(LocalDate date) throws MalformedURLException {
        String urlStr = "http://api.hfs.purdue.edu/menus/v2/locations/" +
                urlComponent() + "/" + date.toString("yyyy-MM-dd");
        return new URL(urlStr);
    }

}
