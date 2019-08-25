package com.example.themoviedb.ui.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.themoviedb.R;
import com.example.themoviedb.SearchActivity;
import com.example.themoviedb.database.MoviesDatabaseHelper;
import com.example.themoviedb.recyclerView.WatchlistMovieAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class WatchlistMovieFragment extends Fragment {

    private static String title;
    private static int page;
    private static WatchlistMovieAdapter adapter;

    public static WatchlistMovieFragment newInstance(int page, String title) {
        WatchlistMovieFragment fragment = new WatchlistMovieFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("someInt", page);
        bundle.putString("someTitle", title);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        page = getArguments().getInt("someInt", 0);
        title = getArguments().getString("someTitle");
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.watchlist_movie_fragment, container, false);

        RecyclerView recyclerView = root.findViewById(R.id.watchlist_movie_recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(root.getContext());
        adapter = new WatchlistMovieAdapter();

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                layoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        FloatingActionButton fab = root.findViewById(R.id.fab);
        fab.setOnClickListener(view -> {
            Context context = view.getContext();
            Intent intent = new Intent(view.getContext(), SearchActivity.class);
            context.startActivity(intent);
        });

        reloadWatchlist();

        return root;
    }

    public void reloadWatchlist() {
        MoviesDatabaseHelper databaseHelper = MoviesDatabaseHelper.getInstance(this.getActivity());
        adapter.setMovies(databaseHelper.getAllMovies(false));
    }
}
