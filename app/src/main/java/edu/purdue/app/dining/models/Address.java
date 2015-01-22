package edu.purdue.app.dining.models;

import org.codehaus.jackson.annotate.JsonProperty;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *  An address for a single dining court.
 *  This is a subobject of the Location object and has correspondence with the /v2/locations API
 *  Created by mike on 1/22/15.
 */
public class Address {

    public static Address fromJson(JSONObject object) throws JSONException {
        Address address = new Address();
        address.setStreet(object.getString("Street"));
        address.setCity(object.getString("City"));
        address.setState(object.getString("State"));
        address.setZipCode(object.getString("ZipCode"));
        address.setCountry(object.getString("Country"));
        address.setCountryCode(object.getString("CountryCode"));
        return address;
    }

    @JsonProperty("Street")
    private String street;

    @JsonProperty("City")
    private String city;

    @JsonProperty("State")
    private String state;

    @JsonProperty("ZipCode")
    private String zipCode;

    @JsonProperty("Country")
    private String country;

    @JsonProperty("CountryCode")
    private String countryCode;

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    @Override
    public String toString() {
        return "Address{" +
                "street='" + street + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", zipCode='" + zipCode + '\'' +
                ", country='" + country + '\'' +
                ", countryCode='" + countryCode + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Address address = (Address) o;

        if (city != null ? !city.equals(address.city) : address.city != null) return false;
        if (country != null ? !country.equals(address.country) : address.country != null)
            return false;
        if (countryCode != null ? !countryCode.equals(address.countryCode) : address.countryCode != null)
            return false;
        if (state != null ? !state.equals(address.state) : address.state != null) return false;
        if (street != null ? !street.equals(address.street) : address.street != null) return false;
        if (zipCode != null ? !zipCode.equals(address.zipCode) : address.zipCode != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = street != null ? street.hashCode() : 0;
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (state != null ? state.hashCode() : 0);
        result = 31 * result + (zipCode != null ? zipCode.hashCode() : 0);
        result = 31 * result + (country != null ? country.hashCode() : 0);
        result = 31 * result + (countryCode != null ? countryCode.hashCode() : 0);
        return result;
    }

}
