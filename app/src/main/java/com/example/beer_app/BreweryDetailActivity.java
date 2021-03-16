package com.example.beer_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;

public class BreweryDetailActivity extends AppCompatActivity {

    public static final String EXTRA_BREWERY_DATA = "BreweryDetailActivity.Brewery";

    private Brewery brewery = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.brewery_description);

        Intent intent = getIntent();

        if (intent != null && intent.hasExtra(EXTRA_BREWERY_DATA)) {
            this.brewery = (Brewery)intent.getSerializableExtra(EXTRA_BREWERY_DATA);

            TextView breweryName = findViewById(R.id.brewery_detail_name);
            breweryName.setText(brewery.getBreweryName());

            TextView breweryYearEstablished = findViewById(R.id.beer_detail_year);
            breweryYearEstablished.setText(brewery.getYearEstablished());

            TextView isOrganic = findViewById(R.id.beer_detail_organic);
            if(brewery.isOrganic()) {
                isOrganic.setText("Organic");
            }
            else {
                isOrganic.setText("Not Organic");
            }

            TextView breweryDescription = findViewById(R.id.brewery_detail_description);
            breweryDescription.setText(brewery.getBreweryDescription());

            TextView breweryWebsite = findViewById(R.id.brewery_detail_website);
            breweryWebsite.setText(brewery.getBreweryWebsite());

            TextView breweryMailingList = findViewById(R.id.brewery_detail_mail);
            breweryMailingList.setText(brewery.getBreweryMailingList());
        }
    }
}