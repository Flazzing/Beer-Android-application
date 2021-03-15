package com.example.beer_app.data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {FavoritesData.class, BeerListData.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    private static volatile AppDatabase INSTANCE;

    private static final int NUM_THREADS = 4;
    static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUM_THREADS);

    public abstract FavoritesDao favoritesDao();
    public abstract BeerListDao beerListDao();

    static AppDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            //lock
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                            context.getApplicationContext(),
                            AppDatabase.class,
                            "favoriteBeers.db"
                    ).build();
                }
            }
        }
        return INSTANCE;
    }
}
