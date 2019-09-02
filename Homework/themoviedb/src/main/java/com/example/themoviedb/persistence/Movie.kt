package com.example.themoviedb.persistence

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.themoviedb.models.MovieModel

@Entity(tableName = "movies")
data class Movie (
    @PrimaryKey
    var id: Int? = 0,
    var posterPath: String? = null,
    var title: String? = null,
    var rating: Boolean = false,
    var isWatched: Boolean = false
)
{
    constructor() : this(0, "", "", false, false)
    constructor(model: MovieModel) : this(0, model.posterPath, model.title, false, false)
}