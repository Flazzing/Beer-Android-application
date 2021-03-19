package com.example.beer_app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.style.RelativeSizeSpan;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.beer_app.data.BeerListData;
import com.example.beer_app.data.BeerListDataList;
import com.example.beer_app.data.FavoritesData;
import com.example.beer_app.data.RandoBeerDataItem;

public class BeerDetailActivity extends AppCompatActivity {
    public static final String EXTRA_BeerList_DATA = "BeerDetailActivity.BeerListDataList";
    public static final String EXTRA_RANDO_BEER_DATA = "BeerDetailActivity.RandoBeerData";

    private FavoritesViewModel favoritesViewModel;
    private BeerListData beerListData;
    private RandoBeerDataItem randoBeerDataItem;
    private boolean isBookmarked;
    private FavoritesData favoritesData;
    private boolean clicked;
    private SwipeRefreshLayout swipeRefreshLayout;
    private Toast favoriteToast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.beer_description);

        this.isBookmarked = false;

        this.clicked = false;

        swipeRefreshLayout = (SwipeRefreshLayout) findViewById ( R.id.Refresher ) ;
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(false);
                if(randoBeerDataItem != null) {
                    Intent intent = new Intent(BeerDetailActivity.this, RandoBeer.class);
                    startActivity(intent);
                }
            }
        });


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

        Intent intentRando = getIntent();
        if (intent != null && intent.hasExtra(EXTRA_RANDO_BEER_DATA)) {
            this.randoBeerDataItem = (RandoBeerDataItem) intent.getSerializableExtra(EXTRA_RANDO_BEER_DATA);
            Log.d("rando beer", randoBeerDataItem.getDescription());
            TextView beerNameTV = findViewById(R.id.beer_name_detail_tv);
            beerNameTV.setText(randoBeerDataItem.getName());
            TextView beerDescriptionTV = findViewById(R.id.beer_description_detail_tv);
            beerDescriptionTV.setText(randoBeerDataItem.getDescription());
            TextView beerABVTV = findViewById(R.id.beer_abv_detail_tv);
            beerABVTV.setText(randoBeerDataItem.getAbv());
            TextView beerOrganicTV = findViewById(R.id.beer_organic_detail_tv);
            beerOrganicTV.setText(randoBeerDataItem.isOrganic());
            TextView beerIsRetiredTV = findViewById(R.id.beer_isRetired_detail_tv);
            beerIsRetiredTV.setText(randoBeerDataItem.getIsRetired());
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
            FavoritesData favoritesData1 = new FavoritesData(beerListData.getId(), beerListData.getName(), beerListData.getDescription());
            this.favoritesViewModel.insertFavoritesData(favoritesData1);
            if (this.favoriteToast != null) {
                this.favoriteToast.cancel();
            }
            String fav_add = "Added to Favorites!";
            SpannableStringBuilder biggerText = new SpannableStringBuilder(fav_add);
            biggerText.setSpan(new RelativeSizeSpan(1.35f), 0, fav_add.length(), 0);
            this.favoriteToast = Toast.makeText(this, biggerText, Toast.LENGTH_SHORT);
            this.favoriteToast.setGravity(Gravity.CENTER, 0, 0);
            this.favoriteToast.show();


           /* this.isBookmarked = !this.isBookmarked;
            menuItem.setChecked(this.isBookmarked);
            if (this.isBookmarked){
                menuItem.setIcon(R.drawable.ic_favorited_beer);
                Log.d("Test", "Bookmarked Added");
                //FavoritesData favoritesData = new FavoritesData("sample_id", beerListData.getName());
                FavoritesData favoritesData1 = new FavoritesData(beerListData.getId(), beerListData.getName());
                Log.d("favorites", favoritesData1.getId());
                this.favoritesViewModel.insertFavoritesData(favoritesData1);
            }
            else {
                menuItem.setIcon(R.drawable.ic_favorite_beer);
                Log.d("Test", "Bookmarked Delete");
               // FavoritesData favoritesData = new FavoritesData("sample_id", beerListData.getName());
              //  this.favoritesViewModel.deleteFavoritesData(favoritesData);
            }*/
        }
    }

    private void shareBeerText() {

      //  String isOrganic;
      //  String productionStatus;
        String name = "Empty";

        if(this.beerListData != null) {
            name = beerListData.getName();
        } else if (this.randoBeerDataItem != null) {
            name = randoBeerDataItem.getName();
        }

        String shareText = getString(
                R.string.share_beer_text,
                name
        );

        Intent sendIntent = new Intent(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, shareText);
        sendIntent.setType("text/plain");

        Intent chooserIntent = Intent.createChooser(sendIntent, null);
        startActivity(chooserIntent);
    }

}
