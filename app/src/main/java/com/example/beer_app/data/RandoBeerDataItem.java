package com.example.beer_app.data;


import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.io.Serializable;
import java.lang.reflect.Type;

public class RandoBeerDataItem implements Serializable {
    private String name;
    private String abv;
    private String description;
    private String isRetired;
    private String isOrganic;

    public RandoBeerDataItem() {
        this.name = null;
        this.abv = null;
        this.description = null;
        this.isRetired = "N";
    }

    public RandoBeerDataItem(String name, String abv, String description, String retired) {
        this.name = name;
        this.abv = abv;
        this.description = description;
        this.isRetired = retired;
    }

    public String getName() {
        return name;
    }

    public String getAbv() {
        return abv;
    }

    public String getDescription() { return description; }

    public String getIsRetired () { return isRetired; }
    public String isOrganic () { return "N/A"; }

    public static class JsonDeserializer implements com.google.gson.JsonDeserializer<RandoBeerDataItem> {
        @Override
        public RandoBeerDataItem deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            JsonObject dataObject = json.getAsJsonObject();
            JsonObject styleObject = dataObject.getAsJsonObject("style");
            return new RandoBeerDataItem(
                    dataObject.getAsJsonPrimitive("name").getAsString(),
                    dataObject.getAsJsonPrimitive("abv").getAsString(),
                    styleObject.getAsJsonPrimitive("description").getAsString(),
                    dataObject.getAsJsonPrimitive("isRetired").getAsString()

                    );
        }
    }
}
