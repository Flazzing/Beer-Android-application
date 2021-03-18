package com.example.beer_app;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.beer_app.data.BeerListData;
import com.example.beer_app.data.BeerListDataList;
import com.example.beer_app.data.BeerListRepository;
import com.example.beer_app.data.RandoBeerData;

import java.util.List;

public class ListBeerViewModel extends AndroidViewModel {

    private BeerListRepository beerListRepository;
    private LiveData<BeerListDataList> beerListRepositoryLiveData;
    private LiveData<RandoBeerData> randomBeerLiveData;

    public ListBeerViewModel(@NonNull Application application) {
        super(application);
        this.beerListRepository = new BeerListRepository(application);
        this.beerListRepositoryLiveData = beerListRepository.getBeerListDataMutableLiveData();
        this.randomBeerLiveData = beerListRepository.getRandomBeerDataMutableLiveData();
    }

    public LiveData<BeerListDataList> getBeerListRepositoryLiveData() {
        return beerListRepositoryLiveData;
    }

    public void loadData(String percent, String year, String apiKey) {
        this.beerListRepository.loadData(percent, year, apiKey);
    }
    public LiveData<RandoBeerData> getRandomBeerLiveData() {
        return randomBeerLiveData;

    }

    public void insertBeer(BeerListData beerListData){
        this.beerListRepository.insertBeer(beerListData);
    }

    public void deleteBeer(BeerListData beerListData){
        this.beerListRepository.deleteBeer(beerListData);
    }

    public void getRandoBeer(String apiKey) {
        this.beerListRepository.getRandoBeer(apiKey);
    }


}
