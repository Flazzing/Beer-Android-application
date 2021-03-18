package com.example.beer_app.data;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BeerListRepository {
    private BeerListDao beerListDao;
    private static final String TAG = BeerListRepository.class.getSimpleName();
    private static final String BASE_URL = "https://sandbox-api.brewerydb.com/v2/beers/";
    private static final String BASE_URL_RANDO = "https://sandbox-api.brewerydb.com/v2/beer/";

    private MutableLiveData<BeerListDataList> beerListDataMutableLiveData;
    private BreweryService breweryService;
    private BreweryService breweryServiceRando;

    private MutableLiveData<RandoBeerData> randomBeerMutableData;

    public LiveData<BeerListDataList> getBeerListDataMutableLiveData() {
        return beerListDataMutableLiveData;
    }
    public LiveData<RandoBeerData> getRandomBeerDataMutableLiveData() {
        return randomBeerMutableData;
    }


    public BeerListRepository(Application application){

        // setup 1
        this.beerListDataMutableLiveData = new MutableLiveData<>();
        this.beerListDataMutableLiveData.setValue(null);

        this.randomBeerMutableData = new MutableLiveData<>();
        this.randomBeerMutableData.setValue(null);

        // setup 2 gson

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(BeerListData.class, new BeerListData.JsonDeserializer())
                .create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        this.breweryService = retrofit.create(BreweryService.class);

        Gson gsonRando = new GsonBuilder()
                .registerTypeAdapter(RandoBeerDataItem.class, new RandoBeerDataItem.JsonDeserializer())
                .create();
        Retrofit retrofitRando = new Retrofit.Builder()
                .baseUrl(BASE_URL_RANDO)
                .addConverterFactory(GsonConverterFactory.create(gsonRando))
                .build();
        this.breweryServiceRando = retrofitRando.create(BreweryService.class);

        AppDatabase db = AppDatabase.getDatabase(application);
        this.beerListDao = db.beerListDao();
    }

    public void getRandoBeer(String apiKey) {
        this.randomBeerMutableData.setValue(null);
        Call<RandoBeerData> req = this.breweryServiceRando.getRandomBeer(apiKey);
        req.enqueue(new Callback<RandoBeerData>() {
            @Override
            public void onResponse(Call<RandoBeerData> call, Response<RandoBeerData> response) {
                if (response.code() == 200){
                    Log.d(TAG, "Success API request: " + call.request().url());
                    randomBeerMutableData.setValue(response.body());
                }
                else {
                    Log.w(TAG,  new Gson().toJson(response));
                    Log.d(TAG, "unsuccessful API request: " + call.request().url());
                    Log.d(TAG, "  -- response status code: " + response.code());
                    Log.d(TAG, "  -- response: " + response.toString());
                }
            }
            @Override
            public void onFailure(Call<RandoBeerData> call, Throwable t) {
                Log.d(TAG,  " on failure unsuccessful API request: " + call.request().url());
                t.printStackTrace();
                Log.d(TAG, String.valueOf(t));
                Log.d(TAG, String.valueOf(t.getCause()));

            }
        });
    }
    public void loadData(String apiKey){
        if (shouldFetchData()){
            this.beerListDataMutableLiveData.setValue(null);
            Call<BeerListDataList> req = this.breweryService.fetchBeer(apiKey);
            req.enqueue(new Callback<BeerListDataList>() {
                @Override
                public void onResponse(Call<BeerListDataList> call, Response<BeerListDataList> response) {
                    if (response.code() == 200){
                        Log.d(TAG, "Success API request: " + call.request().url());
                        beerListDataMutableLiveData.setValue(response.body());
                    }
                    else {
                        Log.w(TAG,  new Gson().toJson(response));
                        Log.d(TAG, "unsuccessful API request: " + call.request().url());
                        Log.d(TAG, "  -- response status code: " + response.code());
                        Log.d(TAG, "  -- response: " + response.toString());
                    }
                }
                @Override
                public void onFailure(Call<BeerListDataList> call, Throwable t) {
                    Log.d(TAG, "unsuccessful API request: " + call.request().url());
                    t.printStackTrace();
                }
            });
        } else {
            Log.d(TAG, "using cached data: beers" );
        }
    }

    private boolean shouldFetchData(){
        BeerListDataList beerListData = this.beerListDataMutableLiveData.getValue();
        if (beerListData == null){
            return true;
        }

        return false;
    }

    public void insertBeer(BeerListData beerListData){
        AppDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                beerListDao.insert(beerListData);
            }
        });
    }

    public void deleteBeer(BeerListData beerListData){
        AppDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                beerListDao.delete(beerListData);
            }
        });
    }

    public LiveData<List<BeerListData>> getAllBeer(){
        return this.beerListDao.getAllBeer();
    }

    public LiveData<List<BeerListData>> getBeerByName(String beerName){
        return this.beerListDao.getBeerByName(beerName);
    }
}
