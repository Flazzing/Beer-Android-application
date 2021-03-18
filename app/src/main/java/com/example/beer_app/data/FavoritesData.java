package com.example.beer_app.data;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "favoriteBeers")
public class FavoritesData {
    @PrimaryKey
    @NonNull
    private String id;

    @NonNull
    private String name;

    private String description;

    public FavoritesData(String id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public String getId() { return this.id; }

    public String getName() { return this.name; }

    public String getDescription() {return this.description; }

}
