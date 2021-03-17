package com.example.beer_app.data;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BreweryListRepository {
    private static final String TAG = BreweryListRepository.class.getSimpleName();
    private static final String BASE_URL = "http://api.brewerydb.com/v2";

    private MutableLiveData<List<BreweryListData>> breweriesList;
    private BreweriesService breweriesService;

    public BreweryListRepository() {
        this.breweriesList = new MutableLiveData<>();
        this.breweriesList.setValue(null);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public LiveData<List<BreweryListData>> getBreweriesList() {
        return this.breweriesList;
    }

    public void loadSearchResults() {
        this.breweriesList.setValue(null);
        Log.d(TAG, "loading search results");
        Call<BreweryListDataList> results = this.breweriesService.searchBreweries();
        results.enqueue(new Callback<BreweryListDataList>() {
            @Override
            public void onResponse(Call<BreweryListDataList> call, Response<BreweryListDataList> response) {
                if(response.code() == 200) {
                    breweriesList.setValue(response.body().breweryListData);
                }
            }

            @Override
            public void onFailure(Call<BreweryListDataList> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}
