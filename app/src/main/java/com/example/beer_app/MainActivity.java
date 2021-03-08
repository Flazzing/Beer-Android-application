package com.example.beer_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button rando_beer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rando_beer = (Button) findViewById(R.id.random_beer_button);
        rando_beer.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Log.d("MainActivity", "clicked random beer");
        Intent intent = new Intent(this, RandoBeer.class);
        startActivity(intent);
    }
}