package com.example.beer_app;

public class Brewery {
    private String breweryName;
    private String breweryShortName;
    private int yearEstablished;
    private boolean isOrganic;
    private String breweryWebsite;
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
