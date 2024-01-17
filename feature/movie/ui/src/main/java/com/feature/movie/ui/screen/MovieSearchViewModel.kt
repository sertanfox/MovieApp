package com.feature.movie.ui.screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.core.common.UiEvent
import com.core.network.ApiConstants
import com.feature.movie.domain.use_cases.GetMovieListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieSearchViewModel @Inject constructor(
    private val getMovieListUseCase: GetMovieListUseCase
) : ViewModel() {

    private val _movieList = mutableStateOf(MovieSearchState())
    val movieList : State<MovieSearchState> get() = _movieList

    private val _query = MutableStateFlow("")
    val query : StateFlow<String> = _query


    init {
        viewModelScope.launch {
            _query.debounce(1000).collectLatest {
                getMovieList(ApiConstants.API_KEY,it)
            }
        }
    }

    fun getMovieList(apiKey:String,query:String) = viewModelScope.launch {
        getMovieListUseCase(apiKey,query).onEach {
            when(it){
                is UiEvent.Success -> {
                    _movieList.value = MovieSearchState(data = it.data)
                }
                is UiEvent.Loading -> {
                    _movieList.value = MovieSearchState(isLoading = true)
                }
                is UiEvent.Error -> {
                    _movieList.value = MovieSearchState(error = it.message.toString())
                }
            }
        }.launchIn(viewModelScope)
    }

    fun setQuery(s:String){
        _query.value = s
    }
}