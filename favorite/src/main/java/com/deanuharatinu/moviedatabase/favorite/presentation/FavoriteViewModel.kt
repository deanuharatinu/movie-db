package com.deanuharatinu.moviedatabase.favorite.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.deanuharatinu.core.domain.Resource
import com.deanuharatinu.core.domain.usecase.MovieUseCase
import com.deanuharatinu.moviedatabase.ui.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class ViewModelState(
  override var isLoading: Boolean = true,
  override var error: String = "",
  var favoriteMovie: List<FavoriteMovieUi> = emptyList()
) : UiState

class FavoriteViewModel @Inject constructor(
  private val movieUseCase: MovieUseCase
) : ViewModel() {
  private val _uiState = MutableStateFlow(ViewModelState())
  val uiState = _uiState.asStateFlow()

  init {
    viewModelScope.launch {
      movieUseCase.getFavoriteMovies().collect { result ->
        val data = (result as Resource.Success).data.map { FavoriteMovieUi.fromDomain(it) }
        _uiState.value = ViewModelState(
          isLoading = false,
          favoriteMovie = data
        )
      }
    }
  }
}