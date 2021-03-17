package com.example.beer_app;

import android.content.Context;
import android.util.Log;
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

    public interface OnFavoritesItemClickListener {
        void onFavoritesItemClick(FavoritesData favoritesData);
    }

    /*public boolean hasBeer(FavoritesData favoritesData) {
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
    }*/

    @NonNull
    @Override
    public FavoritesAdapter.FavoritesItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.favorite_list_item, parent, false);
        return new FavoritesItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoritesAdapter.FavoritesItemViewHolder holder, int position) {
        FavoritesData favoritesData = this.favoritesDataList.get(this.favoritesDataList.size() - position - 1);
        holder.bind(favoritesData);
    }
    public void updateData(List<FavoritesData> favoritesDataList) {
        this.favoritesDataList = favoritesDataList;
        notifyDataSetChanged();
    }

    public void addFavoritesData(FavoritesData favoritesData) {
        Log.d("Favorites adapter","adding favorites");
        this.favoritesDataList.add(favoritesData);
        notifyItemInserted(0);
    }

    public void removeFavoritesData(int position) {
        this.favoritesDataList.remove(favoritesDataList.size() - position - 1);
        notifyItemRemoved(position);
    }

    public FavoritesData getData(int position) {
        return this.favoritesDataList.get(favoritesDataList.size() - position - 1);
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
