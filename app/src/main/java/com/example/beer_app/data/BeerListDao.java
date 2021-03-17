package com.example.beer_app.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface BeerListDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(BeerListData beerListData);

    @Delete
    void delete(BeerListData beerListData);

    @Query("SELECT * from beerList ORDER BY name DESC")
    LiveData<List<BeerListData>> getAllBeer();

    @Query("SELECT * from beerList WHERE name = :beerName LIMIT 1")
    LiveData<List<BeerListData>> getBeerByName(String beerName);
}
