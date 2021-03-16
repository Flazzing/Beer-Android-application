package com.example.beer_app;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class BreweriesViewModel extends ViewModel {
    private BreweriesRepository breweriesRepository;
    private LiveData<List<Brewery>> breweriesList;

    public BreweriesViewModel() {
        this.breweriesRepository = new BreweriesRepository();
        this.breweriesList = this.breweriesRepository.getBreweriesList();
        }

    public LiveData<List<Brewery>> getBreweriesList() {
        return breweriesList;
    }

    public void loadSearchResults() {
        this.breweriesRepository.loadSearchResults();
}




}
