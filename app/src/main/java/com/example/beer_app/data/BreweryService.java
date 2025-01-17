package com.example.beer_app.data;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface BreweryService {
    @GET("beers")
    Call<BeerListDataList> fetchBeer(
            @Query("abv") String percent,
            @Query("year") String year,
            @Query("key") String apiKey
    );

    @GET("random")
    Call<RandoBeerData> getRandomBeer(
        @Query("key") String apiKey
    );

}
