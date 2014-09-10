package edu.purdue.app.labs.model;

/**
 * Created by david on 9/2/14.
 */
public class Lab {

    private Type type;
    private int totalStations;
    private String name;
    private String location;
    private String availableUntil;
    private int availableStations;

    public static enum Type {
        MAC, PC, LINUX;
    }

    public String getAvailableUntil() {
        return availableUntil;
    }

    public void setAvailableUntil(String availableUntil) {
        this.availableUntil = availableUntil;
    }

    public int getAvailableStations() {
        return availableStations;
    }

    public void setAvailableStations(int availableStations) {
        this.availableStations = availableStations;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public int getTotalStations() {
        return totalStations;
    }

    public void setTotalStations(int totalStations) {
        this.totalStations = totalStations;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
