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

    public BeerListData(@NonNull String name,  String productionStatus, String isOrganic, String description, String abv) {
        this.name = name;

        this.productionStatus = productionStatus;
        this.isOrganic = isOrganic;
        this.description = description;
        this.abv = abv;
    }

    @PrimaryKey
    @NonNull
    @SerializedName("name")
    private String name;

    @SerializedName("isRetired")
    private String productionStatus;

    @SerializedName("isOrganic")
    private String isOrganic;

    @SerializedName("description")
    private String description;

    @SerializedName("abv")
    private String abv;


    public void setName(@NonNull String name) {
        this.name = name;
    }


    public void setDescription(String description) {
        this.description = description;
    }

    public void setProductionStatus(String productionStatus) {
        this.productionStatus = productionStatus;
    }

    public void setAbv(String abv) {
        this.abv = abv;
    }

    public String getDescription() {
        return description;
    }

    public void setIsOrganic(String isOrganic) {
        this.isOrganic = isOrganic;
    }

    public String getIsOrganic() {
        return isOrganic;
    }

    @NonNull
    public String getName() {
        return name;
    }

    public String getProductionStatus() {
        return productionStatus;
    }

    public String getAbv() {
        return abv;
    }


    public static class JsonDeserializer implements com.google.gson.JsonDeserializer<BeerListData> {
        @Override
        public BeerListData deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            JsonObject listObj = json.getAsJsonObject();


            String tempDescription= "Description is empty.";
            String tempABV = "Description is empty.";

            if (listObj == null ){
//                Log.d("Check checker", "here is empty");
                tempDescription = "Description is empty.";
                tempABV = "Description is empty.";
            }
            else {
//                Log.d("Check checker", "here is not empty");
                if (listObj.getAsJsonPrimitive("description") != null) {
                    tempDescription = listObj.getAsJsonPrimitive("description").getAsString();
                }

                if (listObj.getAsJsonPrimitive("abv") != null) {
//                    Log.d("checker", "abv");
                    tempABV = listObj.getAsJsonPrimitive("abv").getAsString();
                }

            }

            return new BeerListData(
                    listObj.getAsJsonPrimitive("name").getAsString(),
                    listObj.getAsJsonPrimitive("isRetired").getAsString(),
                    listObj.getAsJsonPrimitive("isOrganic").getAsString(),
                    tempDescription, tempABV
                    );
        }
    }
}
