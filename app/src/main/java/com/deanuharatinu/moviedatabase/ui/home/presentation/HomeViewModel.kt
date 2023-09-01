package com.deanuharatinu.moviedatabase.ui.home.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.deanuharatinu.moviedatabase.core.domain.Resource
import com.deanuharatinu.moviedatabase.core.domain.usecase.MovieInteractor
import com.deanuharatinu.moviedatabase.ui.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class ViewModelState(
  override var isLoading: Boolean = true,
  override var error: String = "",
  var popularMovie: List<PopularMovieUi> = emptyList()
) : UiState


@HiltViewModel
class HomeViewModel @Inject constructor(
  private val movieUseCase: MovieInteractor,
) : ViewModel() {
  private val _uiState = MutableStateFlow(ViewModelState())
  val uiState = _uiState.asStateFlow()

  init {
    viewModelScope.launch {
      movieUseCase.getPopularMovies().collect { result ->
        when (result) {
          is Resource.Success -> {
            val data = result.data.map { PopularMovieUi.fromDomain(it) }
            _uiState.value = ViewModelState(
              isLoading = false,
              popularMovie = data
            )
          }

          is Resource.Error -> {
            val errorMessage = result.message
            _uiState.value = ViewModelState(
              isLoading = false,
              error = errorMessage
            )
          }
        }
      }
    }
  }
}