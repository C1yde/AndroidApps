package com.example.themoviedb.persistence

import android.content.Context

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

import com.example.themoviedb.R

@Database(entities = [Movie::class], version = 1)
abstract class MoviesDatabase : RoomDatabase() {

    abstract fun movieDao(): MovieDao

    companion object {

        @Volatile
        private var INSTANCE: MoviesDatabase? = null

        fun getInstance(context: Context?): MoviesDatabase? {
            if (INSTANCE == null) {
                synchronized(MoviesDatabase::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(context!!.applicationContext,
                                MoviesDatabase::class.java, context.getString(R.string.movies_db_name))
                                .build()
                    }
                }
            }
            return INSTANCE
        }
    }

}