package com.example.beer_app.data;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.lang.reflect.Type;

public class BreweryListData implements Serializable {
    @SerializedName("name")
    private String breweryName;

    @SerializedName("nameShortDisplay")
    private String breweryShortName;

    @SerializedName("description")
    private String breweryDescription;

    @SerializedName("established")
    private int yearEstablished;

    @SerializedName("website")
    private String breweryWebsite;

    @SerializedName("mailingListUrl")
    private String breweryMailingList;


    public BreweryListData(String breweryName, String breweryShortName, int yearEstablished, String breweryDescription, String breweryWebsite, String breweryMailingList) {
        this.breweryName = breweryName;
        this.breweryShortName = breweryShortName;
        this.breweryDescription = breweryDescription;
        this.yearEstablished = yearEstablished;
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

    public String getBreweryWebsite() {
        return breweryWebsite;
    }

    public String getBreweryMailingList() {
        return breweryMailingList;
    }

    public static class JsonDeserializer implements com.google.gson.JsonDeserializer<BreweryListData> {
        @Override
        public BreweryListData deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            JsonObject listObj = json.getAsJsonObject();


            return new BreweryListData(
                    listObj.getAsJsonPrimitive("name").getAsString(),
                    listObj.getAsJsonPrimitive("nameShortDisplay").getAsString(),
                    listObj.getAsJsonPrimitive("established").getAsInt(),
                    listObj.getAsJsonPrimitive("description").getAsString(),
                    listObj.getAsJsonPrimitive("website").getAsString(),+
                    listObj.getAsJsonPrimitive("mailingListURL").getAsString()
            );
        }
    }
}
