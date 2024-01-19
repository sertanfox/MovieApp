package com.feature.movie_details.ui.screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.core.common.UiEvent
import com.core.network.ApiConstants
import com.feature.movie_details.domain.use_cases.GetMovieDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    private val getMovieDetailsUseCase : GetMovieDetailsUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _movieDetailsState = mutableStateOf(MovieDetailsState())
    val movieDetailsState : State<MovieDetailsState> = _movieDetailsState

    init {
        savedStateHandle.getLiveData<String>("id").observeForever {
            it?.let {
                getMovieDetails(it, ApiConstants.API_KEY)
            }
        }
    }
    fun getMovieDetails(id:String, apiKey:String) {
        getMovieDetailsUseCase(id,apiKey).onEach {
            when(it){
                is UiEvent.Error -> {
                    _movieDetailsState.value = MovieDetailsState(error = it.message.toString())
                }
                is UiEvent.Loading -> {
                    _movieDetailsState.value = MovieDetailsState(isLoading = true)
                }
                is UiEvent.Success -> {
                    _movieDetailsState.value = MovieDetailsState(movieDetails =  it.data)
                }
            }
        }.launchIn(viewModelScope)
    }
}