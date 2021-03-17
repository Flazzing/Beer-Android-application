package com.example.beer_app;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.beer_app.data.BeerListData;
import com.example.beer_app.data.BeerListDataList;
import com.example.beer_app.data.FavoritesData;
import com.google.android.material.navigation.NavigationView;

import java.util.List;

public class ListBeer extends AppCompatActivity
        implements ListBeerAdapter.onListBeerItemClickListener {

    private RecyclerView recyclerView;
    private ListBeerViewModel listBeerViewModel;
    private static final String BREWERYDB_APPID = BuildConfig.BREWERYDB_API_KEY;
    private ListBeerAdapter listBeerAdapter;
    private DrawerLayout drawerLayout;
    private RecyclerView drawerListRV;
    private FavoritesAdapter favoritesAdapter;
    private FavoritesViewModel favoritesViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.beer_list);


        //For testing, will move to beer detail once done implmenting
        this.favoritesViewModel = new ViewModelProvider(this,
                new ViewModelProvider.AndroidViewModelFactory(getApplication()))
                .get(FavoritesViewModel.class);

        FavoritesData favoritesData = new FavoritesData("sample_id", "sample_name_from_list_beer");
        this.favoritesViewModel.insertFavoritesData(favoritesData);

        //setup 1
        this.recyclerView = findViewById(R.id.beer_list_RV);
        this.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        this.recyclerView.setHasFixedSize(true);

        // setup 2
        listBeerAdapter = new ListBeerAdapter(this);
        recyclerView.setAdapter(listBeerAdapter);

        this.listBeerViewModel = new ViewModelProvider(this, new ViewModelProvider.AndroidViewModelFactory(getApplication())).get(ListBeerViewModel.class);

        this.listBeerViewModel.loadData(BREWERYDB_APPID);

         this.listBeerViewModel.getBeerListRepositoryLiveData().observe(this, new Observer<BeerListDataList>() {
             @Override
             public void onChanged(BeerListDataList beerListDataList) {
                 listBeerAdapter.updateBeerData(beerListDataList);
             }
         });

    }

    @Override
    public void onForecastItemClick() {

    }
}