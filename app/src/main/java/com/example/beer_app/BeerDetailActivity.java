package com.example.beer_app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.beer_app.data.BeerListData;
import com.example.beer_app.data.BeerListDataList;
import com.example.beer_app.data.FavoritesData;

public class BeerDetailActivity extends AppCompatActivity {
    public static final String EXTRA_BeerList_DATA = "BeerDetailActivity.BeerListDataList";

    private FavoritesViewModel favoritesViewModel;
    private BeerListData beerListData;
    private boolean isBookmarked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.beer_description);

        this.isBookmarked = false;


        this.favoritesViewModel = new ViewModelProvider(this,
                new ViewModelProvider.AndroidViewModelFactory(getApplication()))
                .get(FavoritesViewModel.class);



        Intent intent = getIntent();
        if (intent != null && intent.hasExtra(EXTRA_BeerList_DATA)) {
            this.beerListData = (BeerListData) intent.getSerializableExtra(EXTRA_BeerList_DATA);
            TextView beerNameTV = findViewById(R.id.beer_name_detail_tv);
            beerNameTV.setText(beerListData.getName());
            TextView beerDescriptionTV = findViewById(R.id.beer_description_detail_tv);
            beerDescriptionTV.setText(beerListData.getDescription());
            TextView beerABVTV = findViewById(R.id.beer_abv_detail_tv);
            beerABVTV.setText(beerListData.getAbv());
            TextView beerOrganicTV = findViewById(R.id.beer_organic_detail_tv);
            beerOrganicTV.setText(beerListData.getIsOrganic());
            TextView beerIsRetiredTV = findViewById(R.id.beer_isRetired_detail_tv);
            beerIsRetiredTV.setText(beerListData.getProductionStatus());

        }

        this.favoritesViewModel = new ViewModelProvider(this,
                new ViewModelProvider.AndroidViewModelFactory(getApplication()))
                .get(FavoritesViewModel.class);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.beer_list_menu_bar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.favorite_beer_menu:
                   toggleFavoriteBookmark(item);
                return true;
            case R.id.share_beer_menu:
                shareBeerText();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void toggleFavoriteBookmark(MenuItem menuItem){
        if (this.beerListData != null){
            this.isBookmarked = !this.isBookmarked;
            menuItem.setChecked(this.isBookmarked);
            if (this.isBookmarked){
                menuItem.setIcon(R.drawable.ic_favorited_beer);
                Log.d("Test", "Bookmarked Added");
                FavoritesData favoritesData = new FavoritesData("sample_id", beerListData.getName());
                this.favoritesViewModel.insertFavoritesData(favoritesData);
            }
            else {
                menuItem.setIcon(R.drawable.ic_favorite_beer);
                Log.d("Test", "Bookmarked Delete");
                FavoritesData favoritesData = new FavoritesData("sample_id", beerListData.getName());
                this.favoritesViewModel.deleteFavoritesData(favoritesData);
            }
        }
    }

    private void shareBeerText() {

        String isOrganic;
        String productionStatus;

        if ( this.beerListData.getIsOrganic() == "N"){
            isOrganic = "not organic";
        }
        else {
            isOrganic = "is organic";
        }

        if ( this.beerListData.getProductionStatus() == "N"){
            productionStatus = "no longer in production";
        }
        else {
            productionStatus = "is still producing more.";
        }


        String shareText = getString(
                R.string.share_beer_text,
                this.beerListData.getName(),
                this.beerListData.getAbv(),
                isOrganic,
                productionStatus
        );

        Intent sendIntent = new Intent(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, shareText);
        sendIntent.setType("text/plain");

        Intent chooserIntent = Intent.createChooser(sendIntent, null);
        startActivity(chooserIntent);
    }

}
