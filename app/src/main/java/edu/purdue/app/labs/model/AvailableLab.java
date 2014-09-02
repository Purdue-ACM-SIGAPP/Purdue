package edu.purdue.app.labs.model;

/**
 * Created by david on 9/2/14.
 */
public class AvailableLab extends Lab {

    private String availableUntil;
    private int availableStations;

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
}
