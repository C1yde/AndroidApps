package com.example.themoviedb

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import butterknife.ButterKnife
import com.example.themoviedb.models.MovieResponse
import com.example.themoviedb.recyclerView.SearchMovieAdapter
import com.example.themoviedb.repository.RepositoryProvider
import io.reactivex.Observable
import io.reactivex.ObservableSource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Function
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import java.util.concurrent.TimeUnit

@Suppress("UNCHECKED_CAST")
class SearchActivity : AppCompatActivity() {

    @BindView(R.id.search_movie_recyclerView)
    internal var recyclerView: RecyclerView? = null

    @BindView(R.id.searchText)
    internal var searchTextView: SearchView? = null

    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.search_activity)
        ButterKnife.bind(this)

        val layoutManager = LinearLayoutManager(this)
        val adapter = SearchMovieAdapter()

        val dividerItemDecoration = DividerItemDecoration(recyclerView!!.context,
                layoutManager.orientation)
        recyclerView!!.addItemDecoration(dividerItemDecoration)

        recyclerView!!.setHasFixedSize(true)
        recyclerView!!.layoutManager = layoutManager
        recyclerView!!.adapter = adapter

        getObservableQuery(searchTextView!!)
                .debounce(2, TimeUnit.SECONDS)
                .filter { text -> text.isNotEmpty() && text.length >= 3 }
                .distinctUntilChanged()
                .switchMap({ query: String ->
                    RepositoryProvider.get()!!
                            .provideRepository()
                            .getMovies(query)
                } as Function<String, ObservableSource<MovieResponse>>)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map { it.movies }
                .subscribe { adapter.setMovies(it) }
    }

    private fun getObservableQuery(searchTextView: SearchView): Observable<String> {
        val publishSubject = PublishSubject.create<String>()

        searchTextView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(newText: String): Boolean {
                publishSubject.onNext(newText)
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                publishSubject.onNext(newText)
                return true
            }
        })

        return publishSubject
    }
}