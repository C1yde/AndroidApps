package com.example.themoviedb.repository

import com.example.themoviedb.network.ApiFactory
import com.example.themoviedb.network.clients.MovieDBClient

class RepositoryProvider private constructor() {

    private var repository: MovieRepository? = null

    fun provideRepository(): MovieRepository {
        if (repository == null) {
            repository = MovieRepositoryImpl(getServiceInstance(MovieDBClient::class.java))
        }
        return repository as MovieRepository
    }

    companion object {

        @Volatile
        private var sInstance: RepositoryProvider? = null

        fun get(): RepositoryProvider? {
            if (sInstance == null) {
                synchronized(RepositoryProvider::class.java) {
                    if (sInstance == null) {
                        sInstance = RepositoryProvider()
                    }
                }
            }
            return sInstance
        }

        private fun <T> getServiceInstance(clazz: Class<T>): T {
            return ApiFactory.retrofitInstance!!.create(clazz)
        }
    }
}