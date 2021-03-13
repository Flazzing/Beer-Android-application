package com.example.beer_app.data;

import android.app.Application;

public class FavoritesRepository {
    private FavoritesDao dao;

    public FavoritesRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        this.dao = db.favoritesDao();
    }

    public void insertFavorites(FavoritesData favoritesData) {
        AppDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run(){ dao.insert(favoritesData); }
        });
    }
}
