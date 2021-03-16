package com.example.beer_app;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ListBrewery extends AppCompatActivity {
    private static final String TAG = ListBrewery.class.getSimpleName();

    private RecyclerView breweryListRV;
    private ListBreweryAdapter breweryAdapter;
    private BreweriesViewModel breweriesViewModel;

    protected void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        setContentView(R.layout.brewery_list);

        this.breweryAdapter = new ListBreweryAdapter();

        this.breweryListRV = findViewById(R.id.brewery_list);
        this.breweryListRV.setAdapter(this.breweryAdapter);
        this.breweryListRV.setLayoutManager(new LinearLayoutManager(this));
        this.breweryListRV.setHasFixedSize(true);

        this.breweriesViewModel = new ViewModelProvider(this)
                .get(BreweriesViewModel.class);
        this.breweriesViewModel.getBreweriesList().observe(
                this,
                new Observer<List<Brewery>>() {
                    @Override
                    public void onChanged(List<Brewery> breweries) {
                        ListBreweryAdapter.updateBreweriesList(breweries);
                    }
                }
        );

        breweriesViewModel.loadSearchResults();
    }

    @Override
    public void onBreweryListItemClick(Brewery brewery) {
        Intent intent = new Intent(this, BreweryDetailActivity.class);
        intent.putExtra(BreweryDetailActivity.EXTRA_BREWERY_DATA, brewery);
        startActivity(intent);
    }
}
