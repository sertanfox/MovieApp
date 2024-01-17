package com.core.network

import com.core.network.model.movie_popular.MovieListResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    // movie/popular
    // movie/{movie_id}

    @GET("search/movie")
    suspend fun getMovieList(
        @Query("api_key") apiKey:String,
        @Query("query") query:String
    ):MovieListResponse
}