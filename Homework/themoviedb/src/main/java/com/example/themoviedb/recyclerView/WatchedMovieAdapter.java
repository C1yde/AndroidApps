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

import com.example.themoviedb.Injection;
import com.example.themoviedb.MovieDataSource;
import com.example.themoviedb.R;
import com.example.themoviedb.Utilities;
import com.example.themoviedb.persistence.Movie;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class WatchedMovieAdapter extends RecyclerView.Adapter<WatchedMovieAdapter.WatchedMovieViewHolder> {
    private ArrayList<Movie> mMovies;

    public WatchedMovieAdapter(){
        mMovies = new ArrayList<>();
    }

    public void setMovies(List<Movie> movies){
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
        final Movie currentItem = mMovies.get(index);

        Picasso.get()
                .load(Utilities.getMoviePosterLink(viewHolder.context, currentItem.getPosterPath()))
                .into(viewHolder.moviePoster);
        viewHolder.titleTextView.setText(currentItem.getTitle());
        viewHolder.likeDislikeBtn.setImageResource(currentItem.getRating()
                ? R.drawable.thumb_up
                : R.drawable.thumb_down);

        viewHolder.likeDislikeBtn.setOnClickListener((view)->{
            ImageButton castedView = (ImageButton)view;
            boolean rating = currentItem.getRating();
            castedView.setImageResource(rating
                    ? R.drawable.thumb_down
                    : R.drawable.thumb_up);
            currentItem.setRating(!rating);

            MovieDataSource dataSource = Injection.provideMovieDataSource(viewHolder.context);
            dataSource.insertOrUpdateMovie(currentItem);

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