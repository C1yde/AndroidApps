package com.example.themoviedb.recyclerView

import android.content.Context
import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.example.themoviedb.Injection
import com.example.themoviedb.R
import com.example.themoviedb.Utilities
import com.example.themoviedb.persistence.Movie
import com.squareup.picasso.Picasso
import io.reactivex.CompletableObserver
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.util.*

class WatchlistMovieAdapter : RecyclerView.Adapter<WatchlistMovieAdapter.WatchlistMovieViewHolder>() {
    private val mMovies: ArrayList<Movie> = ArrayList()

    fun setMovies(movies: List<Movie>) {
        mMovies.clear()
        mMovies.addAll(movies)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): WatchlistMovieViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.watchlist_movie_card, viewGroup, false)
        return WatchlistMovieViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: WatchlistMovieViewHolder, i: Int) {
        val currentItem = mMovies[i]

        Picasso.get()
                .load(Utilities.getMoviePosterLink(viewHolder.context, currentItem.posterPath))
                .into(viewHolder.moviePoster)
        viewHolder.titleTextView.text = currentItem.title
        viewHolder.rateButton.setOnClickListener { v -> onClickListener(v, currentItem, i) }
    }

    private fun onClickListener(view: View, movie: Movie, index: Int) {
        val context = view.context
        val dataSource = Injection.provideMovieDataSource(context)
        val dialogClickListener = DialogInterface.OnClickListener{ _: DialogInterface, which: Int ->
            when (which) {
                DialogInterface.BUTTON_POSITIVE -> movie.rating = true
                DialogInterface.BUTTON_NEGATIVE -> movie.rating = false
            }

            movie.isWatched = true
            dataSource.updateMovie(movie)
                    .subscribeOn(Schedulers.io())
                    .subscribe(object : CompletableObserver {
                        override fun onError(e: Throwable) {
                        }
                        override fun onSubscribe(d: Disposable) {
                        }
                        override fun onComplete() {
                            mMovies[index] = movie
                        }

                    })
        }

        val builder = AlertDialog.Builder(context)
        builder.setMessage(context.getString(R.string.rating_question))
                .setPositiveButton(context.getString(R.string.yes_answer), dialogClickListener)
                .setNegativeButton(context.getString(R.string.no_answer), dialogClickListener)
                .show()
    }

    override fun getItemCount(): Int {
        return mMovies.size
    }

    class WatchlistMovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var titleTextView: TextView = itemView.findViewById(R.id.movieTitle)
        var moviePoster: ImageView = itemView.findViewById(R.id.moviePoster)
        var rateButton: ImageButton = itemView.findViewById(R.id.rateButton)
        var context: Context = itemView.context
    }
}
