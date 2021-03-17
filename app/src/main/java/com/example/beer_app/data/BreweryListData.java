package com.example.beer_app.data;

import android.util.Log;

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

    @SerializedName("website")
    private String breweryWebsite;

    @SerializedName("mailingListUrl")
    private String breweryMailingList;


    public BreweryListData(String breweryName, String breweryShortName, String breweryDescription, String breweryWebsite, String breweryMailingList) {
        this.breweryName = breweryName;
        this.breweryShortName = breweryShortName;
        this.breweryDescription = breweryDescription;
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

            String tempName = "N/A";
            String tempShortName = "N/A";
            String tempDescription= "Description is empty.";
            String tempWebsite = "No Website";
            String tempMailingList = "No mailing list";

            if (listObj == null ){
                tempDescription = "Description is empty.";
            }
            else {
                if (listObj.getAsJsonPrimitive("name") != null) {
                    tempName = listObj.getAsJsonPrimitive("name").getAsString();
                }
                if (listObj.getAsJsonPrimitive("nameShortDisplay") != null) {
                    tempShortName = listObj.getAsJsonPrimitive("nameShortDisplay").getAsString();
                }
                if (listObj.getAsJsonPrimitive("description") != null) {
                    tempDescription = listObj.getAsJsonPrimitive("description").getAsString();
                }
                if (listObj.getAsJsonPrimitive("website") != null) {
                    tempWebsite = listObj.getAsJsonPrimitive("website").getAsString();
                }
                if (listObj.getAsJsonPrimitive("mailingListURL") != null) {
                    tempMailingList = listObj.getAsJsonPrimitive("mailingListURL").getAsString();
                }
            }

            return new BreweryListData(
                    tempName,
                    tempShortName,
                    tempDescription,
                    tempWebsite,
                    tempMailingList
            );
        }
    }
}
