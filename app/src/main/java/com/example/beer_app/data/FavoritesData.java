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

    public FavoritesData(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() { return this.id; }

    public String getName() { return this.name; }

}
