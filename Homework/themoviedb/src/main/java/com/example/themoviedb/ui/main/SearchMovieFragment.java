package com.example.themoviedb.ui.main;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.themoviedb.R;
import com.example.themoviedb.models.MovieModel;
import com.example.themoviedb.models.MovieResponse;
import com.example.themoviedb.recyclerView.SearchMovieAdapter;
import com.example.themoviedb.repository.RepositoryProvider;

import java.util.ArrayList;
import java.util.Objects;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class SearchMovieFragment extends Fragment {

    private static String title;
    private static int page;
    private SearchMovieAdapter adapter;
    private TextView emptyListView;

    public static SearchMovieFragment newInstance(int page, String title) {
        SearchMovieFragment fragment = new SearchMovieFragment();
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
        View root = inflater.inflate(R.layout.search_movie_fragment, container, false);

        RecyclerView recyclerView = root.findViewById(R.id.search_movie_recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(root.getContext());
        adapter = new SearchMovieAdapter();

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                layoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        EditText searchTextView = Objects.requireNonNull(getActivity()).findViewById(R.id.searchText);
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

        emptyListView = root.findViewById(R.id.emptyWatchlistTextView);

        return root;
    }

    @SuppressLint("CheckResult")
    private void reloadMovies(@NonNull String searchText) {
        RepositoryProvider.get()
                .provideRepository()
                .getMovies(searchText)
                .flatMap(response -> Single.just(response.getMovies()))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doFinally(() -> emptyListView.setVisibility(View.INVISIBLE))
                .subscribe(adapter::setMovies, throwable -> new Exception(throwable.getMessage()));
    }
}