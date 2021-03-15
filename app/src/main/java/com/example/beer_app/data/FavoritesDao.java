package com.example.beer_app.data;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;

@Dao
public interface FavoritesDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(FavoritesData favoritesData);
}
