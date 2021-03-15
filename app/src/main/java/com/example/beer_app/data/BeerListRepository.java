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

    private MutableLiveData<BeerListData> beerListDataMutableLiveData;
    private BreweryService breweryService;

    public LiveData<BeerListData> getBeerListDataMutableLiveData() {
        return beerListDataMutableLiveData;
    }


    public BeerListRepository(Application application){

        // setup 1
        this.beerListDataMutableLiveData = new MutableLiveData<>();
        this.beerListDataMutableLiveData.setValue(null);

        // setup 2 gson

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(BeerListData.class, new BeerListData.JsonDeserializer())
                .create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        this.breweryService = retrofit.create(BreweryService.class);


        AppDatabase db = AppDatabase.getDatabase(application);
        this.beerListDao = db.beerListDao();
    }

    public void loadData(String apiKey){
        if (shouldFetchData()){
            this.beerListDataMutableLiveData.setValue(null);
            Call<BeerListData> req = this.breweryService.fetchBeer(apiKey);
            req.enqueue(new Callback<BeerListData>() {
                @Override
                public void onResponse(Call<BeerListData> call, Response<BeerListData> response) {
                    if (response.code() == 200){
                        beerListDataMutableLiveData.setValue(response.body());
                    }
                    else {
                        Log.d(TAG, "unsuccessful API request: " + call.request().url());
                        Log.d(TAG, "  -- response status code: " + response.code());
                        Log.d(TAG, "  -- response: " + response.toString());
                    }
                }
                @Override
                public void onFailure(Call<BeerListData> call, Throwable t) {
                    Log.d(TAG, "unsuccessful API request: " + call.request().url());
                    t.printStackTrace();
                }
            });
        } else {
            Log.d(TAG, "using cached forecast data for location: " );
        }
    }

    private boolean shouldFetchData(){
        BeerListData beerListData = this.beerListDataMutableLiveData.getValue();
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
