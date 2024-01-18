package com.core.dataproviders

import com.core.network.ApiService
import javax.inject.Inject

class MovieDataProviders @Inject constructor(
    private val apiService: ApiService
) {

    suspend fun getMovieList(apiKey:String, query:String) = apiService.getMovieList(apiKey,query)
    suspend fun getMovieDetails(id:String,apiKey:String) = apiService.getMovieDetails(id,apiKey)
}