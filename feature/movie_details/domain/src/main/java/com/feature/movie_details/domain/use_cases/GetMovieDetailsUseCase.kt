package com.feature.movie_details.domain.use_cases

import android.util.Log
import com.core.common.UiEvent
import com.feature.movie_details.domain.model.MovieDetails
import com.feature.movie_details.domain.repository.MovieDetailsRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetMovieDetailsUseCase @Inject constructor(
    private val repo : MovieDetailsRepo
) {
    operator fun invoke(id:String,apiKey:String) : Flow<UiEvent<MovieDetails>> = flow {
            emit(UiEvent.Loading())
            emit(UiEvent.Success(repo.getMovieDetails(id,apiKey)))
    }.catch {
        Log.e("GetMovieDetailsUseCase","An error occured : ${it.message}")
        emit(UiEvent.Error(message = it.message.toString()))
    }.flowOn(Dispatchers.IO)
}