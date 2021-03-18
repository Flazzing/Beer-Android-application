package com.example.beer_app;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.beer_app.data.BeerListData;
import com.example.beer_app.data.BeerListDataList;
import com.example.beer_app.data.BeerListRepository;

import java.util.List;

public class ListBeerViewModel extends AndroidViewModel {

    private BeerListRepository beerListRepository;
    private LiveData<BeerListDataList> beerListRepositoryLiveData;

    public ListBeerViewModel(@NonNull Application application) {
        super(application);
        this.beerListRepository = new BeerListRepository(application);
        this.beerListRepositoryLiveData = beerListRepository.getBeerListDataMutableLiveData();
    }

    public LiveData<BeerListDataList> getBeerListRepositoryLiveData() {
        return beerListRepositoryLiveData;
    }

    public void loadData(String percent, String year, String apiKey){
        this.beerListRepository.loadData(percent, year, apiKey);
    }

    public void insertBeer(BeerListData beerListData){
        this.beerListRepository.insertBeer(beerListData);
    }

    public void deleteBeer(BeerListData beerListData){
        this.beerListRepository.deleteBeer(beerListData);
    }


}
