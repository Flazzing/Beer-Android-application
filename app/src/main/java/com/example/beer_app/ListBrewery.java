package com.example.beer_app;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ListBrewery extends AppCompatActivity {
    private static final String TAG = ListBrewery.class.getSimpleName();

    private RecyclerView breweryListRV;
    private ListBreweryAdapter breweryAdapter;

    protected void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        setContentView(R.layout.brewery_list);

        this.breweryAdapter = new ListBreweryAdapter();

        this.breweryListRV = findViewById(R.id.brewery_list);
        this.breweryListRV.setAdapter(this.breweryAdapter);
        this.breweryListRV.setLayoutManager(new LinearLayoutManager(this));
        this.breweryListRV.setHasFixedSize(true);
    }
}
