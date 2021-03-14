package com.example.beer_app;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.beer_app.data.FavoritesData;

public class FavoritesList extends AppCompatActivity
        implements FavoritesAdapter.OnFavoritesItemClickListener {

    private RecyclerView favoritesListRV;
    private FavoritesAdapter favoritesAdapter;
    private static final String TAG = FavoritesList.class.getSimpleName();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.favorites_list);

        this.favoritesListRV = findViewById(R.id.favorites_list_RV);
        this.favoritesListRV.setLayoutManager(new LinearLayoutManager(this));
        this.favoritesListRV.setHasFixedSize(true);

        this.favoritesAdapter = new FavoritesAdapter(this);
        this.favoritesListRV.setAdapter(this.favoritesAdapter);

        FavoritesData favoritesData = new FavoritesData("sample_id", "sample_name");
        Log.d(TAG, favoritesData.getId());
        this.favoritesAdapter.addFavoritesData(favoritesData);

    }

    @Override
    public void onFavoritesItemClick(FavoritesData favoritesData) {

    }
}