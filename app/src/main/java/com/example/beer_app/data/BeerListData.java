package com.example.beer_app.data;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.lang.reflect.Type;

@Entity(tableName = "beerList")
public class BeerListData implements Serializable {

    public BeerListData(@NonNull String name, String displayName,  String productionStatus) {
        this.name = name;
        this.displayName = displayName;
        this.productionStatus = productionStatus;

    }

    @PrimaryKey
    @NonNull
    @SerializedName("name")
    private String name;

    @SerializedName("nameDisplay")
    private String displayName;

    @SerializedName("isRetired")
    private String productionStatus;


    public void setName(@NonNull String name) {
        this.name = name;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public void setProductionStatus(String productionStatus) {
        this.productionStatus = productionStatus;
    }



    @NonNull
    public String getName() {
        return name;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getProductionStatus() {
        return productionStatus;
    }



    public static class JsonDeserializer implements com.google.gson.JsonDeserializer<BeerListData> {
        @Override
        public BeerListData deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            JsonObject listObj = json.getAsJsonObject();
            JsonObject styleObj = listObj.getAsJsonObject("style");

            return new BeerListData(
                    listObj.getAsJsonPrimitive("name").getAsString(),
                    listObj.getAsJsonPrimitive("nameDisplay").getAsString(),
                    listObj.getAsJsonPrimitive("isRetired").getAsString()
                    );
    }
    }

}
