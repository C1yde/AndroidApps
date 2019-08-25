package com.example.themoviedb;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.themoviedb.models.MovieResponse;
import com.example.themoviedb.recyclerView.SearchMovieAdapter;
import com.example.themoviedb.repository.RepositoryProvider;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;

public class SearchActivity extends AppCompatActivity {

    @BindView(R.id.search_movie_recyclerView)
    RecyclerView recyclerView;

    @BindView(R.id.searchText)
    SearchView searchTextView;

    SearchMovieAdapter adapter;

    @SuppressLint("CheckResult")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_activity);
        ButterKnife.bind(this);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        adapter = new SearchMovieAdapter();

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                layoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        getObservableQuery(searchTextView)
                .debounce(2, TimeUnit.SECONDS)
                .filter(text -> !text.isEmpty() && text.length() >= 3)
                .distinctUntilChanged()
                .switchMap((Function<String, ObservableSource<MovieResponse>>) query -> RepositoryProvider.get()
                        .provideRepository()
                        .getMovies(query))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(MovieResponse::getMovies)
                .subscribe(adapter::setMovies);
}

    private Observable<String> getObservableQuery(SearchView searchTextView){
        PublishSubject<String> publishSubject = PublishSubject.create();

        searchTextView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String newText) {
                publishSubject.onNext(newText);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                publishSubject.onNext(newText);
                return true;
            }
        });

        return publishSubject;
    }
}