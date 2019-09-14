package com.example.themoviedb.ui.main

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.themoviedb.Injection
import com.example.themoviedb.R
import com.example.themoviedb.recyclerView.WatchedMovieAdapter
import io.reactivex.android.schedulers.AndroidSchedulers

class WatchedMovieFragment : Fragment() {
    private var adapter: WatchedMovieAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        page = arguments!!.getInt("someInt", 0)
        title = arguments!!.getString("someTitle")
    }

    @SuppressLint("CheckResult")
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.watched_movie_fragment, container, false)

        val recyclerView = root.findViewById<RecyclerView>(R.id.watched_movie_recyclerView)
        val layoutManager = LinearLayoutManager(root.context)
        adapter = WatchedMovieAdapter()

        val dividerItemDecoration = DividerItemDecoration(recyclerView.context,
                layoutManager.orientation)
        recyclerView.addItemDecoration(dividerItemDecoration)

        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter

        updateMovies()

        return root
    }

    companion object {

        private var title: String? = null
        private var page: Int = 0

        fun newInstance(page: Int, title: String): WatchedMovieFragment {
            val fragment = WatchedMovieFragment()
            val bundle = Bundle()
            bundle.putInt("someInt", page)
            bundle.putString("someTitle", title)
            fragment.arguments = bundle
            return fragment
        }
    }

    @SuppressLint("CheckResult")
    fun updateMovies(){
        val dataSource = Injection.provideMovieDataSource(this.context)
        dataSource.watchedMovies
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { adapter!!.setMovies(it) }
    }

    override fun onResume() {
        super.onResume()
        updateMovies()
    }
}