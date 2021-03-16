package com.example.beer_app;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BreweriesRepository {
    private static final String TAG = BreweriesRepository.class.getSimpleName();
    private static final String BASE_URL = "http://api.brewerydb.com/v2";

    private MutableLiveData<List<Brewery>> breweriesList;
    private BreweriesService breweriesService;

    public BreweriesRepository() {
        this.breweriesList = new MutableLiveData<>();
        this.breweriesList.setValue(null);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public LiveData<List<Brewery>> getBreweriesList() {
        return this.breweriesList;
    }

    public void loadSearchResults() {
        this.breweriesList.setValue(null);
        Log.d(TAG, "loading search results");
        Call<BreweriesSearchResults> results = this.breweriesService.searchBreweries();
        results.enqueue(new Callback<BreweriesSearchResults>() {
            @Override
            public void onResponse(Call<BreweriesSearchResults> call, Response<BreweriesSearchResults> response) {
                if(response.code() == 200) {
                    breweriesList.setValue(response.body().data);
                }
            }

            @Override
            public void onFailure(Call<BreweriesSearchResults> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}
