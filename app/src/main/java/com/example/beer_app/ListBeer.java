package com.example.beer_app;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.beer_app.data.BeerListData;
import com.example.beer_app.data.BeerListDataList;

import java.util.List;

public class ListBeer extends AppCompatActivity implements ListBeerAdapter.onListBeerItemClickListener, SharedPreferences.OnSharedPreferenceChangeListener {

    private RecyclerView recyclerView;
    private ListBeerViewModel listBeerViewModel;
    private static final String BREWERYDB_APPID = BuildConfig.BREWERYDB_API_KEY;
    private ListBeerAdapter listBeerAdapter;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.beer_list);

        //setup 1
        this.recyclerView = findViewById(R.id.beer_list_RV);
        this.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        this.recyclerView.setHasFixedSize(true);

        // setup 2
        this.sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        this.sharedPreferences.registerOnSharedPreferenceChangeListener(this);

        // setup 3
         listBeerAdapter = new ListBeerAdapter( this);
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
    protected void onDestroy() {
        this.sharedPreferences.unregisterOnSharedPreferenceChangeListener(this);
        super.onDestroy();
    }

    @Override
    public void onForecastItemClick() {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.pref_item, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                Intent intent = new Intent(this, SettingsActivity.class);
                startActivity(intent);
                setResult(Activity.RESULT_OK);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {

    }
}