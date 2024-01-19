package com.feature.movie.data.mapper

import com.core.network.ApiConstants
import com.core.network.model.movie_popular.MovieListResponse
import com.feature.movie.domain.model.Movie

fun MovieListResponse.toDomainMovieList():List<Movie> {
    return this.results.map {
        Movie(makeFullUrl(it.posterPath),it.id.toString())
    }
}

fun makeFullUrl(path:String) = "${ApiConstants.IMAGE_BASE_URL}$path"