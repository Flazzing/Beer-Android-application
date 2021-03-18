package com.example.beer_app.data;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface BreweryService {
    @GET(".")
    Call<BeerListDataList> fetchBeer(
            @Query("key") String apiKey
    );

    @GET("random")
    Call<RandoBeerData> getRandomBeer(
        @Query("key") String apiKey
    );

}
