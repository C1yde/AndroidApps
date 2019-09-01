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

import com.example.themoviedb.R;
import com.example.themoviedb.Utilities;
import com.example.themoviedb.persistence.MoviesDatabaseHelper;
import com.example.themoviedb.models.MovieModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class WatchlistMovieAdapter extends RecyclerView.Adapter<WatchlistMovieAdapter.WatchlistMovieViewHolder> {
    private ArrayList<MovieModel> mMovies;

    public WatchlistMovieAdapter(){
        mMovies = new ArrayList<>();
    }

    public void setMovies(ArrayList<MovieModel> movies){
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
        final MovieModel currentItem = mMovies.get(i);

        Picasso.get()
                .load(Utilities.getMoviePosterLink(viewHolder.context, currentItem.posterPath))
                .into(viewHolder.moviePoster);
        viewHolder.titleTextView.setText(currentItem.title);
        viewHolder.rateButton.setOnClickListener(v -> onClickListener(v, currentItem, i));
    }

    private void onClickListener(View view, MovieModel movie, int index){
        Context context = view.getContext();
        MoviesDatabaseHelper databaseHelper = MoviesDatabaseHelper.getInstance(context);
        DialogInterface.OnClickListener dialogClickListener = (dialog, which) -> {
            switch (which){
                case DialogInterface.BUTTON_POSITIVE:
                    movie.rating = true;
                    break;

                case DialogInterface.BUTTON_NEGATIVE:
                    movie.rating = false;
                    break;
            }

            movie.isWatched = true;
            databaseHelper.updateMovie(movie);
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