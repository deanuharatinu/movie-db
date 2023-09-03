package com.deanuharatinu.moviedatabase.ui.moviedetail.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.deanuharatinu.core.domain.Resource
import com.deanuharatinu.core.domain.usecase.MovieUseCase
import com.deanuharatinu.moviedatabase.ui.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class ViewModelState(
  override var isLoading: Boolean = true,
  override var error: String = "",
  var movieDetail: MovieDetailUi? = null,
) : UiState

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
  private val movieUseCase: MovieUseCase,
) : ViewModel() {
  private val _uiState = MutableStateFlow(ViewModelState())
  val uiState = _uiState.asStateFlow()

  fun getMovieDetail(movieId: String) {
    viewModelScope.launch {
      movieUseCase.getMovieDetail(movieId).collect { result ->
        when (result) {
          is com.deanuharatinu.core.domain.Resource.Success -> {
            val data = result.data
            _uiState.value = ViewModelState(
              isLoading = false,
              movieDetail = MovieDetailUi.fromDomain(data)
            )
          }

          is com.deanuharatinu.core.domain.Resource.Error -> {
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