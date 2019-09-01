package com.example.themoviedb.ui.main

import android.annotation.SuppressLint
import android.content.Intent
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
import com.example.themoviedb.SearchActivity
import com.example.themoviedb.recyclerView.WatchlistMovieAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton
import io.reactivex.android.schedulers.AndroidSchedulers

class WatchlistMovieFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        page = arguments!!.getInt("someInt", 0)
        title = arguments!!.getString("someTitle")
    }

    @SuppressLint("CheckResult")
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.watchlist_movie_fragment, container, false)

        val recyclerView = root.findViewById<RecyclerView>(R.id.watchlist_movie_recyclerView)
        val layoutManager = LinearLayoutManager(root.context)
        adapter = WatchlistMovieAdapter()

        val dividerItemDecoration = DividerItemDecoration(recyclerView.context,
                layoutManager.orientation)
        recyclerView.addItemDecoration(dividerItemDecoration)

        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter

        val fab = root.findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener { view ->
            val context = view.context
            val intent = Intent(view.context, SearchActivity::class.java)
            context.startActivity(intent)
        }

        val dataSource = Injection.provideMovieDataSource(this.context)
        dataSource.allMovies
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { adapter!!.setMovies(it) }

        return root
    }

    companion object {

        private var title: String? = null
        private var page: Int = 0
        private var adapter: WatchlistMovieAdapter? = null

        fun newInstance(page: Int, title: String): WatchlistMovieFragment {
            val fragment = WatchlistMovieFragment()
            val bundle = Bundle()
            bundle.putInt("someInt", page)
            bundle.putString("someTitle", title)
            fragment.arguments = bundle
            return fragment
        }
    }
}
