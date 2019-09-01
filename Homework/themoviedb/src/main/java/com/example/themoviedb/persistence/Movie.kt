package com.example.themoviedb.persistence

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

import com.example.themoviedb.models.MovieModel

@Entity(tableName = "movies")
class Movie {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "movieid")
    val id: Int = 0

    @ColumnInfo(name = "posterPath")
    var posterPath: String? = null
        private set

    @ColumnInfo(name = "title")
    var title: String? = null
        private set

    @ColumnInfo(name = "rating")
    var rating: Boolean = false
        private set

    @ColumnInfo(name = "iswatched")
    var isWatched: Boolean = false
        private set

    @Ignore
    constructor(title: String) {
        this.title = title
        this.isWatched = false
        this.rating = false
    }

    constructor(movieModel: MovieModel) {
        this.title = movieModel.title
        this.posterPath = movieModel.posterPath
        this.isWatched = false
        this.rating = false
    }

    fun setRating(rating: Boolean?) {
        this.rating = rating!!
    }

    fun setIsWatched(isWatched: Boolean?) {
        this.isWatched = isWatched!!
    }
}
