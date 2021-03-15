package com.example.beer_app.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface FavoritesDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(FavoritesData favoritesData);

    @Delete
    void delete(FavoritesData favoritesData);

    @Query("SELECT * FROM favoriteBeers")
    LiveData<List<FavoritesData>> getAllFavoriteBeers();
}
