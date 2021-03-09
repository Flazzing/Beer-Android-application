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