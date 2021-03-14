package com.example.beer_app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.beer_app.data.FavoritesData;

import java.util.ArrayList;
import java.util.List;

public class FavoritesAdapter extends RecyclerView.Adapter<FavoritesAdapter.FavoritesItemViewHolder> {
    private List<FavoritesData> favoritesDataList;
    private OnFavoritesItemClickListener onFavoritesItemClickListener;

    public FavoritesAdapter(OnFavoritesItemClickListener onFavoritesItemClickListener) {
        this.favoritesDataList = new ArrayList<FavoritesData>();
        this.onFavoritesItemClickListener = onFavoritesItemClickListener;
    }

    public void initalizeResults(List<FavoritesData> favoritesDataList) {
        this.favoritesDataList = favoritesDataList;
        notifyDataSetChanged();
    }

    public interface OnFavoritesItemClickListener {
        void onFavoritesItemClick(FavoritesData favoritesData);
    }

    public boolean hasLocation(FavoritesData favoritesData) {
        if (favoritesData.getName().length() < 1) {
            return true;
        }
        if (this.favoritesDataList != null) {
            for (FavoritesData fd : favoritesDataList) {
                if (fd.getId() == favoritesData.getId()) {
                    return true;
                }
            }
        }
        return false;
    }

    @NonNull
    @Override
    public FavoritesAdapter.FavoritesItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.favorites_list, parent, false);
        return new FavoritesItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoritesAdapter.FavoritesItemViewHolder holder, int position) {
        FavoritesData favoritesData = this.favoritesDataList.get(this.favoritesDataList.size() - position - 1);
        holder.bind(favoritesData);
    }

    public void addLocationData(FavoritesData favoritesData) {
        if (this.favoritesDataList.contains(favoritesData)) {
            return;
        }
        this.favoritesDataList.add(favoritesData);
        notifyItemInserted(0);
    }

    public void removeLocationData(FavoritesData favoritesData) {
        this.favoritesDataList.remove(favoritesData);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (this.favoritesDataList == null) {
            return 0;
        } else {
            return this.favoritesDataList.size();
        }
    }

    class FavoritesItemViewHolder extends RecyclerView.ViewHolder {
        final private TextView favBeerNameTV;

        public FavoritesItemViewHolder(@NonNull View itemView) {
            super(itemView);
            favBeerNameTV = itemView.findViewById(R.id.tv_fav_beer_name);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int index = favoritesDataList.size() - getAdapterPosition() - 1;
                    onFavoritesItemClickListener.onFavoritesItemClick(
                            favoritesDataList.get(index));
                }
            });
        }

        public void bind(FavoritesData favoritesData) {
            Context ctx = this.itemView.getContext();
            favBeerNameTV.setText(favoritesData.getName());
        }
    }
}
