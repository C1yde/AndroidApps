package com.example.themoviedb.recyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.themoviedb.R;
import com.example.themoviedb.Utilities;
import com.example.themoviedb.persistence.MoviesDatabaseHelper;
import com.example.themoviedb.models.MovieModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class WatchedMovieAdapter extends RecyclerView.Adapter<WatchedMovieAdapter.WatchedMovieViewHolder> {
    private ArrayList<MovieModel> mMovies;

    public WatchedMovieAdapter(){
        mMovies = new ArrayList<>();
    }

    public void setMovies(ArrayList<MovieModel> movies){
        mMovies.clear();
        mMovies.addAll(movies);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public WatchedMovieViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.watched_movie_card, viewGroup, false);
        return new WatchedMovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WatchedMovieViewHolder viewHolder, int index) {
        final MovieModel currentItem = mMovies.get(index);

        Picasso.get()
                .load(Utilities.getMoviePosterLink(viewHolder.context, currentItem.posterPath))
                .into(viewHolder.moviePoster);
        viewHolder.titleTextView.setText(currentItem.title);
        viewHolder.likeDislikeBtn.setImageResource(currentItem.rating
                ? R.drawable.thumb_up
                : R.drawable.thumb_down);

        MoviesDatabaseHelper databaseHelper = MoviesDatabaseHelper.getInstance(viewHolder.context);

        viewHolder.likeDislikeBtn.setOnClickListener((view)->{
            ImageButton castedView = (ImageButton)view;
            castedView.setImageResource(currentItem.rating
                    ? R.drawable.thumb_down
                    : R.drawable.thumb_up);
            currentItem.rating = !currentItem.rating;
            databaseHelper.updateMovie(currentItem);
            mMovies.set(index, currentItem);
        });
    }

    @Override
    public int getItemCount() {
        return mMovies.size();
    }

    static class WatchedMovieViewHolder extends RecyclerView.ViewHolder {
        TextView titleTextView;
        ImageView moviePoster;
        ImageButton likeDislikeBtn;
        Context context;

        WatchedMovieViewHolder(View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.movieTitle);
            moviePoster = itemView.findViewById(R.id.moviePoster);
            likeDislikeBtn = itemView.findViewById(R.id.likeDislikeButton);
            context = itemView.getContext();
        }
    }
}