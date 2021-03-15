package com.example.beer_app.data;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

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

    public BeerListData(@NonNull String name, String displayName, String description, String alcoholVolume, String productionStatus, int year) {
        this.name = name;
        this.displayName = displayName;
        this.description = description;
        this.alcoholVolume = alcoholVolume;
        this.productionStatus = productionStatus;
        this.year = year;
    }

    @PrimaryKey
    @NonNull
    @SerializedName("name")
    private String name;

    @SerializedName("nameDisplay")
    private String displayName;

    @SerializedName("description")
    private String description;

    @SerializedName("abv")
    private String alcoholVolume;

    @SerializedName("isRetired")
    private String productionStatus;

    @SerializedName("year")
    private int year;

    public void setName(@NonNull String name) {
        this.name = name;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAlcoholVolume(String alcoholVolume) {
        this.alcoholVolume = alcoholVolume;
    }

    public void setProductionStatus(String productionStatus) {
        this.productionStatus = productionStatus;
    }

    public void setYear(int year) {
        this.year = year;
    }



    @NonNull
    public String getName() {
        return name;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getDescription() {
        return description;
    }

    public String getAlcoholVolume() {
        return alcoholVolume;
    }

    public String getProductionStatus() {
        return productionStatus;
    }

    public int getYear() {
        return year;
    }


    public static class JsonDeserializer implements com.google.gson.JsonDeserializer<BeerListData> {
        @Override
        public BeerListData deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            JsonObject listObj = json.getAsJsonObject();


            return new BeerListData(
                    listObj.getAsJsonPrimitive("name").getAsString(),
                    listObj.getAsJsonPrimitive("nameDisplay").getAsString(),
                    listObj.getAsJsonPrimitive("description").getAsString(),
                    listObj.getAsJsonPrimitive("abv").getAsString(),
                    listObj.getAsJsonPrimitive("isRetired").getAsString(),
                    listObj.getAsJsonPrimitive("year").getAsInt()
                    );
    }
    }

}
