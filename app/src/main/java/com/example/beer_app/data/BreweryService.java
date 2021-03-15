package com.example.beer_app.data;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface BreweryService {
    @GET(".")
    Call<BeerListData> fetchBeer(
            @Query("key") String apiKey
    );

}
