package com.feature.movie_details.ui.screen

import com.feature.movie_details.domain.model.MovieDetails

data class MovieDetailsState(
    val isLoading:Boolean = false,
    val error:String = "",
    val movieDetails: MovieDetails? = null
)
