package com.example.beer_app;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Brewery implements Serializable {
    @SerializedName("name")
    private String breweryName;

    @SerializedName("nameShortDisplay")
    private String breweryShortName;

    @SerializedName("description")
    private String breweryDescription;

    @SerializedName("established")
    private int yearEstablished;

    @SerializedName("isOrganic")
    private boolean isOrganic;

    @SerializedName("website")
    private String breweryWebsite;

    @SerializedName("mailingListUrl")
    private String breweryMailingList;


    public Brewery(String breweryName, String breweryShortName, int yearEstablished, boolean isOrganic, String breweryWebsite, String breweryMailingList) {
        this.breweryName = breweryName;
        this.breweryShortName = breweryShortName;
        this.yearEstablished = yearEstablished;
        this.isOrganic = isOrganic;
        this.breweryWebsite = breweryWebsite;
        this.breweryMailingList = breweryMailingList;
    }


    public String getBreweryName() {
        return breweryName;
    }

    public String getBreweryShortName() {
        return breweryShortName;
    }

    public String getBreweryDescription() {
        return breweryDescription;
    }

    public int getYearEstablished() {
        return yearEstablished;
    }

    public boolean isOrganic() {
        return isOrganic;
    }

    public String getBreweryWebsite() {
        return breweryWebsite;
    }

    public String getBreweryMailingList() {
        return breweryMailingList;
    }
}
