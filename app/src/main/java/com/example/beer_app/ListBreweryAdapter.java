package com.example.beer_app;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ListBreweryAdapter extends RecyclerView.Adapter<ListBreweryAdapter.ListBreweryViewHolder> {
    private List<Brewery> listBreweries;

    public ListBreweryAdapter() {
        this.listBreweries = new List<Brewery>();
    }

    @NonNull
    @Override
    public ListBreweryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.brewery_list_item, parent, false);
        return new ListBreweryAdapter.ListBreweryViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ListBreweryViewHolder holder, int position) {
        Brewery brewery = listBreweries.get(position);
        holder.bind(brewery);

    }

    public void updateBreweriesList(List<Brewery> breweries) {
        this.listBreweries = breweries;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return this.listBreweries.size();
    }

    class ListBreweryViewHolder extends RecyclerView.ViewHolder {
        private TextView breweryName;

        public ListBreweryViewHolder(@NonNull View itemView) {
            super(itemView);
            this.breweryName = itemView.findViewById(R.id.brewery_name);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }

        void bind(Brewery brewery) {
            this.breweryName.setText(brewery.getBreweryShortName());
        }
    }
}
