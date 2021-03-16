package com.example.beer_app;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface BreweriesService {
//    @GET("/breweries/?key=" + BuildConfig.BREWERYDB_API_KEY)
//    Call <BreweriesSearchResults> searchBreweries(@Query("p") String query);

    @GET("/breweries/?key=" + BuildConfig.BREWERYDB_API_KEY)
    Call <BreweriesSearchResults> searchBreweries();
}
