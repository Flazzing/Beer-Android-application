package com.example.beer_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.beer_app.data.BreweryListData;

public class BreweryDetailActivity extends AppCompatActivity {

    public static final String EXTRA_BREWERY_DATA = "BreweryDetailActivity.Brewery";

    private BreweryListData breweryListData = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.brewery_description);

        Intent intent = getIntent();

        if (intent != null && intent.hasExtra(EXTRA_BREWERY_DATA)) {
            this.breweryListData = (BreweryListData)intent.getSerializableExtra(EXTRA_BREWERY_DATA);

            TextView breweryName = findViewById(R.id.brewery_detail_name);
            breweryName.setText(breweryListData.getBreweryName());

            TextView breweryYearEstablished = findViewById(R.id.beer_detail_year);
            breweryYearEstablished.setText(breweryListData.getYearEstablished());

            TextView breweryDescription = findViewById(R.id.brewery_detail_description);
            breweryDescription.setText(breweryListData.getBreweryDescription());

            TextView breweryWebsite = findViewById(R.id.brewery_detail_website);
            breweryWebsite.setText(breweryListData.getBreweryWebsite());

            TextView breweryMailingList = findViewById(R.id.brewery_detail_mail);
            breweryMailingList.setText(breweryListData.getBreweryMailingList());
        }
    }
}