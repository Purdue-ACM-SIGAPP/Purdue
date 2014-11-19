package edu.purdue.app.labs.model;

/**
 * Created by david on 11/14/14.
 */
public class Location {
    private String room;
    private String building;

    public Location(String room, String building) {
        this.room = room;
        this.building = building;
    }

    public String getBuilding() {
        return building;
    }

    public String getRoom() {
        return room;
    }
}
