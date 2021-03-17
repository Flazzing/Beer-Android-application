package com.example.beer_app.data;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class BreweryListDataList {
    @SerializedName("data")
    public ArrayList<BreweryListData> breweryListData;

    public BreweryListDataList() {
        this.breweryListData = null;
    }

    public ArrayList<BreweryListData> getBreweryListData() {
        return breweryListData;
    }
}
