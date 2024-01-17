package com.feature.movie.data.repo

import com.core.dataproviders.MovieDataProviders
import com.feature.movie.data.mapper.toDomainMovieList
import com.feature.movie.domain.model.Movie
import com.feature.movie.domain.repo.MovieRepository
import javax.inject.Inject

class MovieRepoImpl @Inject constructor(
    private val movieDataProviders: MovieDataProviders
) : MovieRepository {
    override suspend fun getMovieList(apiKey: String, query: String): List<Movie> {
        return movieDataProviders.getMovieList(apiKey, query).toDomainMovieList()
    }
}