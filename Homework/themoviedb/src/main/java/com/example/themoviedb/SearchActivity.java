package com.example.themoviedb;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.themoviedb.recyclerView.SearchMovieAdapter;
import com.example.themoviedb.repository.RepositoryProvider;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class SearchActivity extends AppCompatActivity {

    SearchMovieAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_activity);

        RecyclerView recyclerView = findViewById(R.id.search_movie_recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        adapter = new SearchMovieAdapter();

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                layoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        EditText searchTextView = findViewById(R.id.searchText);
        searchTextView.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {}

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                reloadMovies(s.toString());
            }
        });
    }

    @SuppressLint("CheckResult")
    private void reloadMovies(@NonNull String searchText) {
        RepositoryProvider.get()
                .provideRepository()
                .getMovies(searchText)
                .flatMap(response -> Single.just(response.getMovies()))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(adapter::setMovies, throwable -> new Exception(throwable.getMessage()));
    }
}