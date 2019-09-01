package com.example.themoviedb.ui.main;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.themoviedb.Injection;
import com.example.themoviedb.MovieDataSource;
import com.example.themoviedb.R;
import com.example.themoviedb.recyclerView.WatchedMovieAdapter;

import io.reactivex.android.schedulers.AndroidSchedulers;

public class WatchedMovieFragment extends Fragment {

    private static String title;
    private static int page;
    private WatchedMovieAdapter adapter;

    public static WatchedMovieFragment newInstance(int page, String title) {
        WatchedMovieFragment fragment = new WatchedMovieFragment();
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

    @SuppressLint("CheckResult")
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.watched_movie_fragment, container, false);

        RecyclerView recyclerView = root.findViewById(R.id.watched_movie_recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(root.getContext());
        adapter = new WatchedMovieAdapter();

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                layoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        MovieDataSource dataSource = Injection.provideMovieDataSource(this.getContext());
        dataSource.getWatchedMovies()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(adapter::setMovies);

        return root;
    }
}