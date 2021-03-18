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
    private int timezoneOffsetSeconds;

    public RandoBeerDataItem() {
        this.name = null;
        this.abv = null;
    }

    public RandoBeerDataItem(String name, String abv) {
        this.name = name;
        this.abv = abv;
    }

    public String getName() {
        return name;
    }

    public String getAbv() {
        return abv;
    }


    public static class JsonDeserializer implements com.google.gson.JsonDeserializer<RandoBeerDataItem> {
        @Override
        public RandoBeerDataItem deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            JsonObject dataObject = json.getAsJsonObject();
            return new RandoBeerDataItem(
                    dataObject.getAsJsonPrimitive("name").getAsString(),
                    dataObject.getAsJsonPrimitive("abv").getAsString()
            );
        }
    }
}
