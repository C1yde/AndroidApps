package com.example.themoviedb.recyclerView;

import android.annotation.SuppressLint;
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
import com.example.themoviedb.models.MovieModel;
import com.example.themoviedb.persistence.Movie;
import com.google.android.material.snackbar.Snackbar;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import io.reactivex.android.schedulers.AndroidSchedulers;

public class SearchMovieAdapter extends RecyclerView.Adapter<SearchMovieAdapter.SearchMovieViewHolder> {
    private ArrayList<MovieModel> mMovies;

    public SearchMovieAdapter(){
        mMovies = new ArrayList<>();
    }

    public void setMovies(ArrayList<MovieModel> movies){
        mMovies.clear();
        mMovies.addAll(movies);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public SearchMovieViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.search_movie_card, viewGroup, false);
        return new SearchMovieViewHolder(view);
    }

    @SuppressLint("CheckResult")
    @Override
    public void onBindViewHolder(@NonNull SearchMovieViewHolder viewHolder, int i) {
        final MovieModel currentItem = mMovies.get(i);

        Picasso.get()
                .load(Utilities.getMoviePosterLink(viewHolder.context, currentItem.posterPath))
                .into(viewHolder.moviePoster);
        viewHolder.titleTextView.setText(currentItem.title);

        MovieDataSource dataSource = Injection.provideMovieDataSource(viewHolder.context);

        dataSource.getMovie(currentItem.title)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(addedMovie -> viewHolder.addRemoveButton.setImageResource(addedMovie != null
                        ? R.drawable.check
                        : R.drawable.plus));

        viewHolder.addRemoveButton.setOnClickListener((view)->{
            final ImageButton castedView = (ImageButton)view;

            dataSource.getMovie(currentItem.title)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(persistMovie -> {
                        int snackBarText;
                        if (persistMovie != null){
                            castedView.setImageResource(R.drawable.plus);
                            dataSource.deleteMovie(persistMovie);
                            snackBarText = R.string.movieRemoved;
                        } else{
                            castedView.setImageResource(R.drawable.check);
                            Movie newMovie = new Movie(currentItem);
                            dataSource.insertOrUpdateMovie(newMovie);
                            snackBarText = R.string.movieAdded;
                        }

                        Snackbar snackbar = Snackbar.make(view, snackBarText, Snackbar.LENGTH_LONG);
                        snackbar.setAction(R.string.undoString, new UndoListener());
                        snackbar.show();
                    });
        });
    }

    public class UndoListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            // Code to undo the user's last action
        }
    }

    @Override
    public int getItemCount() {
        return mMovies.size();
    }

    static class SearchMovieViewHolder extends RecyclerView.ViewHolder {
        TextView titleTextView;
        ImageView moviePoster;
        ImageButton addRemoveButton;
        Context context;

        SearchMovieViewHolder(View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.movieTitle);
            moviePoster = itemView.findViewById(R.id.moviePoster);
            addRemoveButton = itemView.findViewById(R.id.addRemoveButton);
            context = itemView.getContext();
        }
    }
}