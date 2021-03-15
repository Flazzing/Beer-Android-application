package com.example.beer_app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ListBeerAdapter extends RecyclerView.Adapter<ListBeerAdapter.ListBeeerItemViewHolder>{

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

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class ListBeeerItemViewHolder extends RecyclerView.ViewHolder {

        public ListBeeerItemViewHolder(@NonNull View itemView) {
            super(itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // whwat to do when clicked
                }
            });
        }

        public void bind(){

        }

    } // class ListBeeerItemViewHolder


}
