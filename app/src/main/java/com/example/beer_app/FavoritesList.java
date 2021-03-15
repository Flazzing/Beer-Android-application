package com.example.beer_app;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.beer_app.data.FavoritesData;

public class FavoritesList extends AppCompatActivity
        implements FavoritesAdapter.OnFavoritesItemClickListener {

    private RecyclerView favoritesListRV;
    private FavoritesAdapter favoritesAdapter;
    private static final String TAG = FavoritesList.class.getSimpleName();
    private FavoritesViewModel favoritesViewModel;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.favorites_list);

        this.favoritesViewModel = new ViewModelProvider(this,
                new ViewModelProvider.AndroidViewModelFactory(getApplication()))
                .get(FavoritesViewModel.class);

        this.favoritesListRV = findViewById(R.id.favorites_list_RV);
        this.favoritesListRV.setLayoutManager(new LinearLayoutManager(this));
        this.favoritesListRV.setHasFixedSize(true);

        this.favoritesAdapter = new FavoritesAdapter(this);
        this.favoritesListRV.setAdapter(this.favoritesAdapter);

        FavoritesData favoritesData = new FavoritesData("sample_id", "sample_name");
        FavoritesData favoritesData2 = new FavoritesData("sample_id2", "sample_name2");

        Log.d(TAG, favoritesData.getId());
        this.favoritesAdapter.addFavoritesData(favoritesData);
        this.favoritesAdapter.addFavoritesData(favoritesData2);


        ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT ) {

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {
                //Remove swiped item from list and notify the RecyclerView
                int position = viewHolder.getAdapterPosition();
                favoritesAdapter.removeFavoritesData(position);
            }
        };
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        itemTouchHelper.attachToRecyclerView(favoritesListRV);

    }

    @Override
    public void onFavoritesItemClick(FavoritesData favoritesData) {

    }
}