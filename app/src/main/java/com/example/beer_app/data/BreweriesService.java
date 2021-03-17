package com.example.beer_app.data;

import com.example.beer_app.BuildConfig;

import retrofit2.Call;
import retrofit2.http.GET;

public interface BreweriesService {
//    @GET("/breweries/?key=" + BuildConfig.BREWERYDB_API_KEY)
//    Call <BreweriesSearchResults> searchBreweries(@Query("p") String query);

    @GET("/breweries/?key=" + BuildConfig.BREWERYDB_API_KEY)
    Call <BreweryListDataList> searchBreweries();
}
