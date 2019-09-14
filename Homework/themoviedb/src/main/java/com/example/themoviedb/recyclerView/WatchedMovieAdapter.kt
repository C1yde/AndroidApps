package com.example.themoviedb.recyclerView

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
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

class WatchedMovieAdapter : RecyclerView.Adapter<WatchedMovieAdapter.WatchedMovieViewHolder>() {
    private val mMovies: ArrayList<Movie> = ArrayList()

    fun setMovies(movies: List<Movie>) {
        mMovies.clear()
        mMovies.addAll(movies)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): WatchedMovieViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.watched_movie_card, viewGroup, false)
        return WatchedMovieViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: WatchedMovieViewHolder, index: Int) {
        val currentItem = mMovies[index]

        Picasso.get()
                .load(Utilities.getMoviePosterLink(viewHolder.context, currentItem.posterPath))
                .into(viewHolder.moviePoster)
        viewHolder.titleTextView.text = currentItem.title
        viewHolder.likeDislikeBtn.setImageResource(if (currentItem.rating)
            R.drawable.thumb_up
        else
            R.drawable.thumb_down)

        viewHolder.likeDislikeBtn.setOnClickListener { view ->
            val castedView = view as ImageButton
            val rating = currentItem.rating
            currentItem.rating = !rating

            val dataSource = Injection.provideMovieDataSource(viewHolder.context)
            dataSource.updateMovie(currentItem)
                    .subscribeOn(Schedulers.io())
                    .subscribe(object : CompletableObserver {
                        override fun onError(e: Throwable) {
                        }
                        override fun onSubscribe(d: Disposable) {
                        }
                        override fun onComplete() {
                            castedView.setImageResource(if (rating)
                                R.drawable.thumb_down
                            else
                                R.drawable.thumb_up)

                            mMovies[index] = currentItem
                        }
                    })
        }
    }

    override fun getItemCount(): Int {
        return mMovies.size
    }

    class WatchedMovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var titleTextView: TextView = itemView.findViewById(R.id.movieTitle)
        var moviePoster: ImageView = itemView.findViewById(R.id.moviePoster)
        var likeDislikeBtn: ImageButton = itemView.findViewById(R.id.likeDislikeButton)
        var context: Context = itemView.context
    }
}