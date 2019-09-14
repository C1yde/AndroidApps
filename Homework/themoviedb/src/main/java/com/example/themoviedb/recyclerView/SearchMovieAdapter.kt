package com.example.themoviedb.recyclerView

import android.annotation.SuppressLint
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
import com.example.themoviedb.models.MovieModel
import com.example.themoviedb.persistence.Movie
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso
import io.reactivex.CompletableObserver
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableMaybeObserver
import io.reactivex.schedulers.Schedulers
import java.util.*



class SearchMovieAdapter : RecyclerView.Adapter<SearchMovieAdapter.SearchMovieViewHolder>() {
    private val mMovies: ArrayList<MovieModel> = ArrayList()

    fun setMovies(movies: ArrayList<MovieModel>?) {
        mMovies.clear()
        mMovies.addAll(movies!!)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): SearchMovieViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.search_movie_card, viewGroup, false)
        return SearchMovieViewHolder(view)
    }

    @SuppressLint("CheckResult")
    override fun onBindViewHolder(viewHolder: SearchMovieViewHolder, i: Int) {
        val currentItem = mMovies[i]

        Picasso.get()
                .load(Utilities.getMoviePosterLink(viewHolder.context, currentItem.posterPath))
                .into(viewHolder.moviePoster)
        viewHolder.titleTextView.text = currentItem.title

        val dataSource = Injection.provideMovieDataSource(viewHolder.context)

        dataSource.getMovie(currentItem.title)
                .subscribeOn(Schedulers.io())
                .subscribe { addedMovie ->
                    viewHolder.addRemoveButton.setImageResource(if (addedMovie != null)
                        R.drawable.check
                    else
                        R.drawable.plus)
                }

        viewHolder.addRemoveButton.setOnClickListener { view ->
            val castedView = view as ImageButton

            dataSource.getMovie(currentItem.title)
                    .subscribeOn(Schedulers.io())
                    .subscribe(object : DisposableMaybeObserver<Movie>() {
                        override fun onSuccess(movie: Movie) {
                            castedView.setImageResource(R.drawable.plus)
                            dataSource.deleteMovie(movie)

                            showSnackBar(view, R.string.movieRemoved)
                        }

                        override fun onComplete() {
                            castedView.setImageResource(R.drawable.check)
                            val newMovie = Movie(currentItem)
                            dataSource.insertMovie(newMovie)
                                    .subscribeOn(Schedulers.io())
                                    .subscribe(object : CompletableObserver{
                                        override fun onError(e: Throwable) {
                                        }
                                        override fun onSubscribe(d: Disposable) {
                                        }
                                        override fun onComplete() {
                                            showSnackBar(view, R.string.movieAdded)
                                        }

                                    })
                        }

                        override fun onError(e: Throwable) {
                        }
                    })
        }
    }

    fun showSnackBar(view: View, titleResourceId: Int){
        val snackBar = Snackbar.make(view, titleResourceId, Snackbar.LENGTH_LONG)
        snackBar.setAction(R.string.undoString, UndoListener())
        snackBar.show()
    }

    inner class UndoListener : View.OnClickListener {

        override fun onClick(v: View) {
            // Code to undo the user's last action
        }
    }

    override fun getItemCount(): Int {
        return mMovies.size
    }

    class SearchMovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var titleTextView: TextView = itemView.findViewById(R.id.movieTitle)
        var moviePoster: ImageView = itemView.findViewById(R.id.moviePoster)
        var addRemoveButton: ImageButton = itemView.findViewById(R.id.addRemoveButton)
        var context: Context = itemView.context
    }
}