package edu.purdue.app.dining.models;

import org.codehaus.jackson.annotate.JsonProperty;
import org.joda.time.DateTime;
import org.joda.time.LocalTime;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *  The start and end-time that a meal is open
 *  This is a separate class so the object mapper can work should we choose to parse the api that way
 *  It also contains some business logic for parsing the dates into more useful time objects
 *
 *  Created by mike.hockerman on 1/22/15.
 */
public class OpenHours {

    public static OpenHours fromJson(JSONObject object) throws JSONException {
        OpenHours hours = new OpenHours();
        hours.setStartTimeStr(object.getString("StartTime"));
        hours.setEndTimeStr(object.getString("EndTime"));
        return hours;
    }

    @JsonProperty("StartTime")
    private String startTimeStr;

    @JsonProperty("EndTime")
    private String endTimeStr;

    public String getStartTimeStr() {
        return startTimeStr;
    }

    public void setStartTimeStr(String startTimeStr) {
        this.startTimeStr = startTimeStr;
    }

    public String getEndTimeStr() {
        return endTimeStr;
    }

    public void setEndTimeStr(String endTimeStr) {
        this.endTimeStr = endTimeStr;
    }

    /** Returns the exact start time as a jodatime object */
    public LocalTime getStartTime() {
        int hour = Integer.parseInt(startTimeStr.split(":")[0]);
        int minute = Integer.parseInt(startTimeStr.split(":")[1]);
        LocalTime t = new LocalTime(hour, minute);
        return t;
    }

    /** Returns the exact end time as a jodatime object */
    public LocalTime getEndTime() {
        int hour = Integer.parseInt(endTimeStr.split(":")[0]);
        int minute = Integer.parseInt(endTimeStr.split(":")[1]);
        LocalTime t = new LocalTime(hour, minute);
        return t;
    }

    @Override
    public String toString() {
        return "OpenHours{" +
                "startTimeStr='" + startTimeStr + '\'' +
                ", endTimeStr='" + endTimeStr + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OpenHours openHours = (OpenHours) o;

        if (endTimeStr != null ? !endTimeStr.equals(openHours.endTimeStr) : openHours.endTimeStr != null)
            return false;
        if (startTimeStr != null ? !startTimeStr.equals(openHours.startTimeStr) : openHours.startTimeStr != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = startTimeStr != null ? startTimeStr.hashCode() : 0;
        result = 31 * result + (endTimeStr != null ? endTimeStr.hashCode() : 0);
        return result;
    }

}
