package com.example.themoviedb.recyclerView;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.PopupMenu;
import androidx.recyclerview.widget.RecyclerView;

import com.example.themoviedb.R;
import com.example.themoviedb.database.MoviesDatabaseHelper;
import com.example.themoviedb.models.Movie;
import com.google.android.material.snackbar.Snackbar;

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

        viewHolder.menuButton.setOnClickListener(v -> showPopupMenu(v, currentItem, i));
    }

    private void showPopupMenu(View view, Movie movie, int index){
        final PopupMenu popupMenu = new PopupMenu(view.getContext(), view);

        MoviesDatabaseHelper databaseHelper = MoviesDatabaseHelper.getInstance(view.getContext());
        popupMenu.inflate(R.menu.watchlist_movie_menu);
        popupMenu.setOnMenuItemClickListener(item -> {
            switch (item.getItemId()) {
                case R.id.action_add_to_watched:
                    if (movie.isWatched){
                        Snackbar snackbar = Snackbar.make(view, "Movie already added to Watched list.", Snackbar.LENGTH_LONG);
                        snackbar.show();
                        return true;
                    }

                    movie.isWatched = true;
                    databaseHelper.updateMovie(movie);
                    mMovies.set(index, movie);
                    return true;

                case R.id.action_rate_movie:
                    DialogInterface.OnClickListener dialogClickListener = (dialog, which) -> {
                        switch (which){
                            case DialogInterface.BUTTON_POSITIVE:
                                movie.rating = true;
                                break;

                            case DialogInterface.BUTTON_NEGATIVE:
                                movie.rating = false;
                                break;
                        }

                        databaseHelper.updateMovie(movie);
                        mMovies.set(index, movie);
                    };

                    AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                    builder.setMessage("Did you like this movie?").setPositiveButton("Yes", dialogClickListener)
                            .setNegativeButton("No", dialogClickListener).show();
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