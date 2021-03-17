package com.example.beer_app;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.beer_app.data.BreweryListData;

import java.util.List;

public class ListBreweryAdapter extends RecyclerView.Adapter<ListBreweryAdapter.ListBreweryViewHolder> {
    private List<BreweryListData> listBreweries;
    private OnBreweryClickListener breweryCLickListener;

    interface OnBreweryClickListener {
        void onBreweryClicked(BreweryListData breweryListData);
    }

    public ListBreweryAdapter(OnBreweryClickListener listener) {
        this.breweryCLickListener = listener;
    }

    public void updateBreweriesList(List<BreweryListData> breweries) {
        this.listBreweries = breweries;
        notifyDataSetChanged();
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
        BreweryListData breweryListData = listBreweries.get(position);
        holder.bind(breweryListData);

    }

    @Override
    public int getItemCount() {
        if(this.listBreweries != null) {
            return this.listBreweries.size();
        }
        else {
            return 0;
        }
    }

    class ListBreweryViewHolder extends RecyclerView.ViewHolder {
        final private TextView breweryName;
        final private TextView breweryDescription;
        final private TextView breweryWebsite;
        final private TextView breweryMailingList;

        public ListBreweryViewHolder(@NonNull View itemView) {
            super(itemView);
            this.breweryName = itemView.findViewById(R.id.brewery_name);
            this.breweryDescription = itemView.findViewById(R.id.brewery_description);
            this.breweryWebsite = itemView.findViewById(R.id.brewery_website);
            this.breweryMailingList = itemView.findViewById(R.id.brewery_mail);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    breweryCLickListener.onBreweryClicked(
                            listBreweries.get(getAdapterPosition())
                    );
                }
            });
        }

        void bind(BreweryListData breweryListData) {
            this.breweryName.setText(breweryListData.getBreweryShortName());
            this.breweryDescription.setText(breweryListData.getBreweryDescription());
            this.breweryWebsite.setText(breweryListData.getBreweryWebsite());
            this.breweryMailingList.setText(breweryListData.getBreweryMailingList());
        }
    }
}
