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
import androidx.recyclerview.widget.RecyclerView;

import com.example.themoviedb.Injection;
import com.example.themoviedb.MovieDataSource;
import com.example.themoviedb.R;
import com.example.themoviedb.Utilities;
import com.example.themoviedb.persistence.Movie;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class WatchlistMovieAdapter extends RecyclerView.Adapter<WatchlistMovieAdapter.WatchlistMovieViewHolder> {
    private ArrayList<Movie> mMovies;

    public WatchlistMovieAdapter(){
        mMovies = new ArrayList<>();
    }

    public void setMovies(List<Movie> movies){
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

        Picasso.get()
                .load(Utilities.getMoviePosterLink(viewHolder.context, currentItem.getPosterPath()))
                .into(viewHolder.moviePoster);
        viewHolder.titleTextView.setText(currentItem.getTitle());
        viewHolder.rateButton.setOnClickListener(v -> onClickListener(v, currentItem, i));
    }

    private void onClickListener(View view, Movie movie, int index){
        Context context = view.getContext();
        MovieDataSource dataSource = Injection.provideMovieDataSource(context);
        DialogInterface.OnClickListener dialogClickListener = (dialog, which) -> {
            switch (which){
                case DialogInterface.BUTTON_POSITIVE:
                    movie.setRating(true);
                    break;

                case DialogInterface.BUTTON_NEGATIVE:
                    movie.setRating(false);
                    break;
            }

            movie.setIsWatched(true);
            dataSource.insertOrUpdateMovie(movie);
            mMovies.set(index, movie);
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(context.getString(R.string.rating_question))
                .setPositiveButton(context.getString(R.string.yes_answer), dialogClickListener)
                .setNegativeButton(context.getString(R.string.no_answer), dialogClickListener)
                .show();
    }

    @Override
    public int getItemCount() {
        return mMovies.size();
    }

    static class WatchlistMovieViewHolder extends RecyclerView.ViewHolder {
        TextView titleTextView;
        ImageView moviePoster;
        ImageButton rateButton;
        Context context;

        WatchlistMovieViewHolder(View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.movieTitle);
            moviePoster = itemView.findViewById(R.id.moviePoster);
            rateButton = itemView.findViewById(R.id.rateButton);
            context = itemView.getContext();
        }
    }
}