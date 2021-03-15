package com.example.beer_app.data;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class BeerListDataList {

    @SerializedName("data")
    private ArrayList<BeerListData> beerListData;

    public BeerListDataList(){
        this.beerListData = null;
    }
    public ArrayList<BeerListData> getBeerListData() {
        return beerListData;
    }
}
