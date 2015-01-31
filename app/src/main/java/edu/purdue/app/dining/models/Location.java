package edu.purdue.app.dining.models;

import org.codehaus.jackson.annotate.JsonProperty;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 *  A location object returned from the /v2/locations API
 *  The API also returns a MASSIVE object that talks about the normal hours that the dining court
 *  is open. Im not including it in this object as it would probably take a thousand loc to parse
 *  and we dont need it because each individual meal already has hours.
 *
 *  Created by mike on 1/22/15.
 */
public class Location {

    public static Location fromJson(JSONObject object) throws JSONException {
        Location location = new Location();
        location.setName(object.getString("Name"));
        location.setFormalName(object.getString("FormalName"));
        location.setAddress(Address.fromJson(object.getJSONObject("Address")));
        location.setPhoneNumber(object.getString("PhoneNumber"));
        location.setLatitude(object.getDouble("Latitude"));
        location.setLongitude(object.getDouble("Longitude"));
        List<String> images = new ArrayList<String>();
        JSONArray imagesAr = object.getJSONArray("Images");
        for (int i = 0; i < imagesAr.length(); i++) {
            images.add(imagesAr.getString(i));
        }
        location.setImages(images);
        return location;
    }

    @JsonProperty("Name")
    private String name;

    @JsonProperty("FormalName")
    private String formalName;

    @JsonProperty("Address")
    private Address address;

    @JsonProperty("PhoneNumber")
    private String phoneNumber;

    @JsonProperty("Latitude")
    private double latitude;

    @JsonProperty("Longitude")
    private double longitude;

    /** This appears to be a list of UUIDs that correspond to images somewhere. Its probably worthless. */
    @JsonProperty("Images")
    private List<String> images;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFormalName() {
        return formalName;
    }

    public void setFormalName(String formalName) {
        this.formalName = formalName;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    @Override
    public String toString() {
        return "Location{" +
                "name='" + name + '\'' +
                ", formalName='" + formalName + '\'' +
                ", address=" + address +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", images=" + images +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Location location = (Location) o;

        if (Double.compare(location.latitude, latitude) != 0) return false;
        if (Double.compare(location.longitude, longitude) != 0) return false;
        if (address != null ? !address.equals(location.address) : location.address != null)
            return false;
        if (formalName != null ? !formalName.equals(location.formalName) : location.formalName != null)
            return false;
        if (images != null ? !images.equals(location.images) : location.images != null)
            return false;
        if (name != null ? !name.equals(location.name) : location.name != null) return false;
        if (phoneNumber != null ? !phoneNumber.equals(location.phoneNumber) : location.phoneNumber != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = name != null ? name.hashCode() : 0;
        result = 31 * result + (formalName != null ? formalName.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (phoneNumber != null ? phoneNumber.hashCode() : 0);
        temp = Double.doubleToLongBits(latitude);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(longitude);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (images != null ? images.hashCode() : 0);
        return result;
    }

}
