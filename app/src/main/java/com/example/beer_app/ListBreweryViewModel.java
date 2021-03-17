package com.example.beer_app;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.beer_app.data.BreweryListData;
import com.example.beer_app.data.BreweryListRepository;

import java.util.List;

public class ListBreweryViewModel extends ViewModel {
    private BreweryListRepository breweryListRepository;
    private LiveData<List<BreweryListData>> breweriesList;

    public ListBreweryViewModel() {
        this.breweryListRepository = new BreweryListRepository();
        this.breweriesList = this.breweryListRepository.getBreweriesList();
        }

    public LiveData<List<BreweryListData>> getBreweriesList() {
        return breweriesList;
    }

    public void loadSearchResults() {
        this.breweryListRepository.loadSearchResults();
}




}
