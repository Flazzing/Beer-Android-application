package com.example.beer_app;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;

import com.example.beer_app.data.FavoritesData;
import com.example.beer_app.data.FavoritesRepository;

public class FavoritesViewModel extends AndroidViewModel {
    private FavoritesRepository repository;

    public FavoritesViewModel(Application application) {
        super(application);
        this.repository = new FavoritesRepository(application);
    }

    public void insertFavoritesData(FavoritesData favoritesData) {
        this.repository.insertFavorites(favoritesData);
    }
}
