package com.example.beer_app.data;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface BreweryService {
    @GET("beers")
    Call<BeerListDataList> fetchBeer(
            @Query("abv") String percent,
            @Query("isOrganic") String organic,
            @Query("year") String year,
            @Query("key") String apiKey
    );

}
