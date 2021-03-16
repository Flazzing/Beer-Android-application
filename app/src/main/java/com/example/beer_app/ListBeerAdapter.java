package com.example.beer_app;

import android.content.Context;
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

public class ListBeerAdapter extends RecyclerView.Adapter<ListBeerAdapter.ListBeeerItemViewHolder>{


    private BeerListDataList beerListDataList;
    private ListBeerAdapter.onListBeerItemClickListener onListBeerItemClickListener;

    public interface onListBeerItemClickListener {
        void onForecastItemClick();
    }

    public ListBeerAdapter(ListBeerAdapter.onListBeerItemClickListener onListBeerItemClickListener) {
        this.onListBeerItemClickListener = onListBeerItemClickListener;
    }

    @NonNull
    @Override
    public ListBeeerItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.beer_list_item, parent, false);
        return new ListBeerAdapter.ListBeeerItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ListBeeerItemViewHolder holder, int position) {
        holder.bind(this.beerListDataList.getBeerListData().get(position));
    }

    public void updateBeerData(BeerListDataList beerListData){
        this.beerListDataList = beerListData;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (this.beerListDataList != null){
            return this.beerListDataList.getBeerListData().size();
        }
        else {
            return 0;
        }
    }

    class ListBeeerItemViewHolder extends RecyclerView.ViewHolder {

        final private TextView textView;

        public ListBeeerItemViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.beerName_tv);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // whwat to do when clicked
                }
            });
        }

        public void bind(BeerListData beerListData){
            this.textView.setText(beerListData.getName());
        }

    } // class ListBeerItemViewHolder


}
