package com.example.beer_app;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.beer_app.data.BreweryListData;

import java.util.List;

public class ListBrewery extends AppCompatActivity implements ListBreweryAdapter.OnBreweryClickListener {
    private static final String TAG = ListBrewery.class.getSimpleName();

    private RecyclerView breweryListRV;
    private ListBreweryAdapter breweryAdapter;
    private ListBreweryViewModel listBreweryViewModel;

    protected void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        setContentView(R.layout.brewery_list);

        //setup recyclerview
        this.breweryListRV = findViewById(R.id.brewery_list);
        this.breweryListRV.setLayoutManager(new LinearLayoutManager(this));
        this.breweryListRV.setHasFixedSize(true);

        //setup adapter and attach to recyclerview
        this.breweryAdapter = new ListBreweryAdapter(this);
        this.breweryListRV.setAdapter(this.breweryAdapter);

        //setup viewmodel
        this.listBreweryViewModel = new ViewModelProvider(this)
                .get(ListBreweryViewModel.class);
        listBreweryViewModel.loadSearchResults();
        this.listBreweryViewModel.getBreweriesList().observe(
                this,
                new Observer<List<BreweryListData>>() {
                    @Override
                    public void onChanged(List<BreweryListData> breweries) {
                        ListBreweryAdapter.updateBreweriesList(breweries);
                    }
                }
        );
    }

    @Override
    public void onBreweryClicked(BreweryListData breweryListData) {
        Intent intent = new Intent(this, BreweryDetailActivity.class);
        intent.putExtra(BreweryDetailActivity.EXTRA_BREWERY_DATA, breweryListData);
        startActivity(intent);
    }
}
