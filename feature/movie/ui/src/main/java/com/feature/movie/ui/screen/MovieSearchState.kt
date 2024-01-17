package com.feature.movie.ui.screen

import com.feature.movie.domain.model.Movie

data class MovieSearchState(
    val isLoading:Boolean = false,
    val data:List<Movie>? = null,
    val error:String = ""
)
