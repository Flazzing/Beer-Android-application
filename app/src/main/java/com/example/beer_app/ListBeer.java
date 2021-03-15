package com.example.beer_app;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.beer_app.data.BeerListData;

import java.util.List;

public class ListBeer extends AppCompatActivity implements ListBeerAdapter.onListBeerItemClickListener {

    private RecyclerView recyclerView;
    private ListBeerViewModel listBeerViewModel;
    private static final String BREWERYDB_APPID = BuildConfig.BREWERYDB_API_KEY;
    private ListBeerAdapter listBeerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.beer_list);

        //setup 1
        this.recyclerView = findViewById(R.id.beer_list_RV);
        this.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        this.recyclerView.setHasFixedSize(true);

        // setup 2
         listBeerAdapter = new ListBeerAdapter( this);
        recyclerView.setAdapter(listBeerAdapter);

         this.listBeerViewModel = new ViewModelProvider(this, new ViewModelProvider.AndroidViewModelFactory(getApplication())).get(ListBeerViewModel.class);

        this.listBeerViewModel.loadData(BREWERYDB_APPID);

         this.listBeerViewModel.getAllBookmarkedLocation().observe(this, new Observer<List<BeerListData>>() {
             @Override
             public void onChanged(List<BeerListData> beerListData) {
                for (int i = 0; i < beerListData.size(); i++){
                    Log.d("testest", beerListData.get(i).getName());
                }
                 listBeerAdapter.updateBeerData(beerListData);
             }
         });

         this.listBeerViewModel.getAllBookmarkedLocation().observe(this, new Observer<List<BeerListData>>() {
             @Override
             public void onChanged(List<BeerListData> beerListData) {
                 listBeerAdapter.updateBeerData(beerListData);
                 if (beerListData != null){
                     listBeerAdapter.updateBeerData(beerListData);
                 }
             }
         });

    }

    @Override
    public void onForecastItemClick() {

    }
}