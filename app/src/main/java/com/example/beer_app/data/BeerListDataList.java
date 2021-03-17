package com.example.beer_app.data;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class BeerListDataList implements Serializable {

    @SerializedName("data")
    private ArrayList<BeerListData> beerListDataLists;

    public ArrayList<BeerListData> getBeerListData() {
        return beerListDataLists;
    }

    public BeerListDataList(){
        this.beerListDataLists = null;
    }


}
