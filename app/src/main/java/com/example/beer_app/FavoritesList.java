package com.example.beer_app;

import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.style.RelativeSizeSpan;
import android.util.Log;
import android.view.Gravity;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.beer_app.data.FavoritesData;
import com.example.beer_app.data.RandoBeerDataItem;

import java.util.List;

public class FavoritesList extends AppCompatActivity
        implements FavoritesAdapter.OnFavoritesItemClickListener {

    private RecyclerView favoritesListRV;
    private FavoritesAdapter favoritesAdapter;
    private static final String TAG = FavoritesList.class.getSimpleName();
    private FavoritesViewModel favoritesViewModel;
    private Toast favoriteToast;
    private Toast deleteToast;



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


        this.favoritesViewModel.getAllFavoriteBeers().observe(this,
                new Observer<List<FavoritesData>>() {
                    @Override
                    public void onChanged(List<FavoritesData> favoritesDataList) {
                        favoritesAdapter.updateData(favoritesDataList);

                    }
                });

        //FavoritesData favoritesData = new FavoritesData("sample_id", "sample_name");
        //FavoritesData favoritesData2 = new FavoritesData("sample_id2", "sample_name2");

        //Log.d(TAG, favoritesData.getId());
        //this.favoritesAdapter.addFavoritesData(favoritesData);
        //this.favoritesAdapter.addFavoritesData(favoritesData2);


        ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT ) {

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {
                //Remove swiped item from list and notify the RecyclerView
                int position = viewHolder.getAdapterPosition();
                FavoritesData favoritesData = favoritesAdapter.getData(position);
                favoritesAdapter.removeFavoritesData(position);
                favoritesViewModel.deleteFavoritesData(favoritesData);
                if (deleteToast != null) {
                    deleteToast.cancel();
                }
                String deleteText= "Removed from Favorites";
                SpannableStringBuilder biggerText = new SpannableStringBuilder(deleteText);
                biggerText.setSpan(new RelativeSizeSpan(1.35f), 0, deleteText.length(), 0);
                deleteToast = Toast.makeText(FavoritesList.this, biggerText, Toast.LENGTH_SHORT);
                deleteToast.setGravity(Gravity.CENTER, 0, 0);
                deleteToast.show();

            }
        };
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        itemTouchHelper.attachToRecyclerView(favoritesListRV);

    }

    @Override
    public void onFavoritesItemClick(FavoritesData favoritesData) {
        if (this.favoriteToast != null) {
            this.favoriteToast.cancel();
        }
        String descrip = favoritesData.getDescription();
        SpannableStringBuilder biggerText = new SpannableStringBuilder(descrip);
        biggerText.setSpan(new RelativeSizeSpan(1.35f), 0, descrip.length(), 0);
        this.favoriteToast = Toast.makeText(this, biggerText, Toast.LENGTH_SHORT);
        this.favoriteToast.setGravity(Gravity.CENTER, 0, 0);
        this.favoriteToast.show();


    }
}