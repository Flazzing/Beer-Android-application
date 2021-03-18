package com.example.beer_app.data;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class RandoBeerData implements Serializable {

    @SerializedName("data")
    private RandoBeerDataItem randoBeerDataItem;

    public RandoBeerDataItem getRandoBeerDataItem() {
        return this.randoBeerDataItem;
    }

    public RandoBeerData() {
        this.randoBeerDataItem = null;
    }

}
