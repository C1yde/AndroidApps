package com.example.themoviedb.recyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.PopupMenu;
import androidx.recyclerview.widget.RecyclerView;

import com.example.themoviedb.R;
import com.example.themoviedb.models.Movie;

import java.util.ArrayList;

public class WatchlistMovieAdapter extends RecyclerView.Adapter<WatchlistMovieAdapter.WatchlistMovieViewHolder> {
    private ArrayList<Movie> mMovies;

    public WatchlistMovieAdapter(){
        mMovies = new ArrayList<>();
    }

    public void setMovies(ArrayList<Movie> movies){
        mMovies.clear();
        mMovies.addAll(movies);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public WatchlistMovieViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.watchlist_movie_card, viewGroup, false);
        return new WatchlistMovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WatchlistMovieViewHolder viewHolder, int i) {
        final Movie currentItem = mMovies.get(i);

        currentItem.setPoster(viewHolder.moviePoster);
        viewHolder.titleTextView.setText(currentItem.title);

        viewHolder.menuButton.setOnClickListener(this::showPopupMenu);
    }

    private void showPopupMenu(View view){
        final PopupMenu popupMenu = new PopupMenu(view.getContext(), view);
        popupMenu.inflate(R.menu.watchlist_movie_menu);
        popupMenu.setOnMenuItemClickListener(item -> {
            switch (item.getItemId()) {
                case 0:
                    // item ID 0 was clicked
                    return true;
                case 1:
                    // item ID 1 was clicked
                    return true;
            }
            return false;
        });

        popupMenu.show();
    }

    @Override
    public int getItemCount() {
        return mMovies.size();
    }

    static class WatchlistMovieViewHolder extends RecyclerView.ViewHolder {
        TextView titleTextView;
        ImageView moviePoster;
        ImageButton menuButton;
        Context context;

        WatchlistMovieViewHolder(View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.movieTitle);
            moviePoster = itemView.findViewById(R.id.moviePoster);
            menuButton = itemView.findViewById(R.id.menuButton);
            context = itemView.getContext();
        }
    }
}