package com.example.beer_app;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.beer_app.data.BeerListData;
import com.example.beer_app.data.RandoBeerData;
import com.example.beer_app.data.RandoBeerDataItem;

public class RandoBeer extends AppCompatActivity {
    private static final String BREWERYDB_APPID = BuildConfig.BREWERYDB_API_KEY;
    private static final String TAG = RandoBeer.class.getSimpleName();
    private ListBeerViewModel listBeerViewModel;
    private RandoBeerDataItem randoBeerDataItem;
    private boolean clicked;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rando_beer);

        this.clicked = false;


        this.listBeerViewModel = new ViewModelProvider(this, new ViewModelProvider.AndroidViewModelFactory(getApplication())).get(ListBeerViewModel.class);

        this.listBeerViewModel.getRandomBeerLiveData().observe(this, new Observer<RandoBeerData>() {
            @Override
            public void onChanged(RandoBeerData randoBeerData) {
                if(clicked) {
                    Log.d(TAG, "got beer");
                    clicked = false;
                      Intent intent = new Intent(RandoBeer.this, BeerDetailActivity.class);
                      intent.putExtra(BeerDetailActivity.EXTRA_RANDO_BEER_DATA, randoBeerData.getRandoBeerDataItem());
                      startActivity(intent);
                }

            }
        });

        Button rando_beer_btn = (Button)findViewById(R.id.rando_beer_trigger);
        rando_beer_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listBeerViewModel.getRandoBeer(BREWERYDB_APPID);
                Log.d(TAG, "getting beer");
                clicked = true;
            }
        });
    }

}