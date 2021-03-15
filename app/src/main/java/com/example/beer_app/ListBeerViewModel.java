package com.example.beer_app;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.beer_app.data.BeerListData;
import com.example.beer_app.data.BeerListRepository;

import java.util.List;

public class ListBeerViewModel extends AndroidViewModel {

    private BeerListRepository beerListRepository;
    private LiveData<BeerListData> beerListRepositoryLiveData;

    public ListBeerViewModel(@NonNull Application application) {
        super(application);
        this.beerListRepository = new BeerListRepository(application);
        this.beerListRepositoryLiveData = beerListRepository.getBeerListDataMutableLiveData();
    }

    public void loadData(String apiKey){
        this.beerListRepository.loadData(apiKey);
    }

    public void insertBeer(BeerListData beerListData){
        this.beerListRepository.insertBeer(beerListData);
    }

    public void deleteBeer(BeerListData beerListData){
        this.beerListRepository.deleteBeer(beerListData);
    }

    public LiveData<List<BeerListData>> getAllBookmarkedLocation(){
        return this.beerListRepository.getAllBeer();
    }

    public LiveData<List<BeerListData>> getBookmarkedLocationByName(String beerName){
        return this.beerListRepository.getBeerByName(beerName);
    }

}
