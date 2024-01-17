package com.core.network.model.movie_popular


import com.google.gson.annotations.SerializedName

data class MovieListResponse(
    val page: Int = 0,
    val results: List<MovieDto> = listOf(),
    @SerializedName("total_pages")
    val totalPages: Int = 0,
    @SerializedName("total_results")
    val totalResults: Int = 0
)