package com.example.beer_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.beer_app.data.BeerListData;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import java.util.List;


public class MainActivity extends AppCompatActivity implements View.OnClickListener, ListBeerAdapter.onListBeerItemClickListener {
    private static final String TAG = MainActivity.class.getSimpleName();

      /*
     * To use your own API key, create a file called `gradle.properties` in your
     * GRADLE_USER_HOME directory (this will usually be `$HOME/.gradle/` in MacOS/Linux and
     * `$USER_HOME/.gradle/` in Windows), and add the following line:
     *
     *   BREWERYDB_API_KEY="<put_your_own_BREWERYDB_API_key_here>"
     *
     * https://www.brewerydb.com make an account and get api key under developer tab
     */

    private static final String BREWERYDB_APPID = BuildConfig.BREWERYDB_API_KEY;

    private Button rando_beer;
    private Button list_of_beer_btn;
    private Button list_of_brewery_btn;
    private Button favorites_btn;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        rando_beer = (Button) findViewById(R.id.random_beer_button);
        rando_beer.setOnClickListener(this);

        list_of_beer_btn = findViewById(R.id.beers_button);
        list_of_beer_btn.setOnClickListener(this);

        list_of_brewery_btn = findViewById(R.id.breweries_button);
        list_of_brewery_btn.setOnClickListener(this);

        favorites_btn = findViewById(R.id.favorites_beer_button);
        favorites_btn.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.beers_button:
                Log.d(TAG, "beer list intent launch");
                Intent beer_list_intent = new Intent(this, ListBeer.class);
                this.startActivity(beer_list_intent);
                break;

            case R.id.random_beer_button:
                Log.d(TAG, "random beer intent launch");
                Intent intent = new Intent(this, RandoBeer.class);
                this.startActivity(intent);
                break;
            case R.id.breweries_button:
                Log.d(TAG, "brewery list intent launch");
                Intent brewery_list_intent = new Intent(this, ListBrewery.class);
                this.startActivity(brewery_list_intent);
                break;
            case R.id.favorites_beer_button:
                Log.d(TAG, "favorites list intent launch");
                Intent favorites_list_intent = new Intent(this, FavoritesList.class);
                this.startActivity(favorites_list_intent);
            default:
                break;

        }



   }

    @Override
    public void onForecastItemClick() {

    }
}