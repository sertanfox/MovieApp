package com.feature.movie_details.data.repository

import com.core.dataproviders.MovieDataProviders
import com.feature.movie_details.data.mapper.toDomain
import com.feature.movie_details.domain.model.MovieDetails
import com.feature.movie_details.domain.repository.MovieDetailsRepo
import javax.inject.Inject

class MovieDetailsRepoImpl @Inject constructor(
    private val movieDataProviders: MovieDataProviders
) : MovieDetailsRepo {
    override suspend fun getMovieDetails(id: String, apiKey: String): MovieDetails {
        return movieDataProviders.getMovieDetails(id,apiKey).toDomain()
    }
}