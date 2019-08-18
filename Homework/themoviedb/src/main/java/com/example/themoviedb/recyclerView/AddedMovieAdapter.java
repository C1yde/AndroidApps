package com.example.themoviedb.recyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.themoviedb.R;
import com.example.themoviedb.models.MovieModel;

import java.util.ArrayList;

public class AddedMovieAdapter extends RecyclerView.Adapter<AddedMovieAdapter.AddedMovieViewHolder> {
    private ArrayList<MovieModel> mMovies;

    public AddedMovieAdapter(){
        mMovies = new ArrayList<>();
    }

    public void setMovies(ArrayList<MovieModel> movies){
        mMovies.clear();
        mMovies.addAll(movies);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public AddedMovieViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.added_movie_card, viewGroup, false);
        return new AddedMovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AddedMovieViewHolder viewHolder, int i) {
        final MovieModel currentItem = mMovies.get(i);

        currentItem.setPoster(viewHolder.moviePoster);
        viewHolder.titleTextView.setText(currentItem.title);
    }

    @Override
    public int getItemCount() {
        return mMovies.size();
    }

    static class AddedMovieViewHolder extends RecyclerView.ViewHolder {
        TextView titleTextView;
        ImageView moviePoster;

        AddedMovieViewHolder(View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.movieTitle);
            moviePoster = itemView.findViewById(R.id.moviePoster);
        }
    }
}