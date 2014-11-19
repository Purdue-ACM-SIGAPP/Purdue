package edu.purdue.app.labs.model;

/**
 * Created by david on 9/2/14.
 */
public abstract class Lab {

    private int totalStations;
    private int availStations;
    private String name;
    private Location location;
    private int availableStations;

    /**
     * This is meant to refer to ALL stations of all types
     * @return
     */
    public int getAvailableStations() {
        return availableStations;
    }

    public void setAvailableStations(int availableStations) {
        this.availableStations = availableStations;
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

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public abstract Object getHours();
    public abstract void setHours(Object hours);
}
