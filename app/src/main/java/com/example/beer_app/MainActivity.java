package com.example.beer_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button rando_beer;
    private Button list_of_beer_btn;
    private static final String BREWERYDB_APPID = "3034caa39d772f54b5c636c2e7b4733e";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rando_beer = (Button) findViewById(R.id.random_beer_button);
        rando_beer.setOnClickListener(this);

        list_of_beer_btn = findViewById(R.id.beers_button);
        list_of_beer_btn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.beers_button:
                Intent beer_list_intent = new Intent(this, ListBeer.class);
                this.startActivity(beer_list_intent);
                break;

            case R.id.random_beer_button:
                Intent intent = new Intent(this, RandoBeer.class);
                this.startActivity(intent);
                break;
            default:
                break;

        }



   }
}