package com.example.beer_app;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.beer_app.data.BeerListDao;
import com.example.beer_app.data.BeerListData;
import com.example.beer_app.data.BeerListDataList;

import java.util.List;

import static android.content.ContentValues.TAG;

public class ListBeerAdapter extends RecyclerView.Adapter<ListBeerAdapter.ListBeerItemViewHolder>{


    private BeerListDataList beerListDataList;
    private ListBeerAdapter.onListBeerItemClickListener onListBeerItemClickListener;

    public interface onListBeerItemClickListener {
        void onListBeerItemClick(BeerListData beerListData);
    }


    public ListBeerAdapter(ListBeerAdapter.onListBeerItemClickListener onListBeerItemClickListener) {
        this.onListBeerItemClickListener = onListBeerItemClickListener;
    }

    @NonNull
    @Override
    public ListBeerItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.beer_list_item, parent, false);
        return new ListBeerAdapter.ListBeerItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ListBeerItemViewHolder holder, int position) {
        holder.bind(this.beerListDataList.getBeerListData().get(position));
    }

    public void updateBeerData(BeerListDataList beerListData){
        this.beerListDataList = beerListData;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (this.beerListDataList != null){
            Log.d(TAG, "fetching new forecast data for year: " + beerListDataList.getBeerListData() );
            return this.beerListDataList.getBeerListData().size();
        }
        else {
            return 0;
        }
    }

    class ListBeerItemViewHolder extends RecyclerView.ViewHolder {

        final private TextView textView_name;
        final private TextView textView_isRetired;
        final private TextView textView_isOrganic;
        final private TextView textView_description;
        final private TextView textView_abv;

        public ListBeerItemViewHolder(@NonNull View itemView) {
            super(itemView);
            textView_name = itemView.findViewById(R.id.beerName_tv);
            textView_description = itemView.findViewById(R.id.beer_description_tv);
            textView_isOrganic = itemView.findViewById(R.id.beerNameIsOrganic_tv);
            textView_isRetired = itemView.findViewById(R.id.beerNameIsRetired_tv);
            textView_abv = itemView.findViewById(R.id.beerNameABV_tv);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onListBeerItemClickListener.onListBeerItemClick(
                            beerListDataList.getBeerListData().get(getAdapterPosition())
                    );
                }
            });
        }

        public void bind(BeerListData beerListData){
            this.textView_name.setText(beerListData.getName());
            this.textView_isRetired.setText(beerListData.getProductionStatus());
            this.textView_isOrganic.setText(beerListData.getIsOrganic());
            this.textView_description.setText(beerListData.getDescription());
            this.textView_abv.setText(beerListData.getAbv());
        }

    } // class ListBeerItemViewHolder


}
